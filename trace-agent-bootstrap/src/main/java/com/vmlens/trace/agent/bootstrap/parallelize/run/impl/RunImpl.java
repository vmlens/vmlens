package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.AfterContext;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.ExecuteBeforeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.*;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.list.linked.TLongLinkedList;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.emptyList;

public class RunImpl implements Run {

    private final ThreadPoolMap threadPoolMap = new ThreadPoolMap();
    private final ReentrantLock lock;
    private final Condition threadActiveCondition;
    private final WaitNotifyStrategy waitNotifyStrategy;
    private final RunStateMachine runStateMachine;
    private final int loopId;
    private final int runId;

    public RunImpl(ReentrantLock lock,
                   WaitNotifyStrategy waitNotifyStrategy,
                   RunStateMachine runStateMachine,
                   int loopId,
                   int runId) {
        this.waitNotifyStrategy = waitNotifyStrategy;
        this.runStateMachine = runStateMachine;
        this.lock = lock;
        this.threadActiveCondition = lock.newCondition();
        this.loopId = loopId;
        this.runId = runId;
    }

    @Override
    public void after(AfterContext afterContext) {
        lock.lock();
        try {
            afterContext.runtimeEvent().setRunId(runId);
            afterContext.runtimeEvent().setLoopId(loopId);
            runStateMachine.after(AfterContextForStateMachine.of(afterContext),SendEvent.create(afterContext,this));
            if(afterContext.runtimeEvent().asInterleaveActionFactory() != null) {
                waitNotifyStrategy.notifyAndWaitTillActive(afterContext.threadLocalDataWhenInTest(),
                        runStateMachine,
                        threadActiveCondition,
                        SendEvent.create(afterContext,this));
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void newTask(NewTaskContext newTaskContext) {
        lock.lock();
        try {
            ThreadLocalWhenInTest threadLocalWhenInTest = runStateMachine.processNewTestTask(newTaskContext,
                    this,
                    SendEvent.create(newTaskContext,this));
            if(threadLocalWhenInTest != null) {
                waitNotifyStrategy.notifyAndWaitTillActive(threadLocalWhenInTest,
                        runStateMachine,
                        threadActiveCondition,
                        SendEvent.create(newTaskContext,this));
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public ActualRun end(ThreadLocalForParallelize threadLocalForParallelize) {
        lock.lock();
        try {
            return runStateMachine.end(threadLocalForParallelize);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void threadStartedByPool(ThreadStartedByPoolContext context) {
        lock.lock();
        try {
            threadPoolMap.add(context);
            runStateMachine.beforeLockExitWaitOrThreadStart(context.threadStartEvent(),
                    context.threadLocalDataWhenInTest(),
                    new SendEvent(context.queueIn(),this));
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void threadJoinedByPool(JoinAction threadJoinedAction) {
        TLinkedList<TLinkableWrapper<Thread>> threadList = emptyList();
        lock.lock();
        try {
            threadList = threadPoolMap.process(threadJoinedAction);
        } finally {
            lock.unlock();
        }
        // join must happen outside the lock
        TLongLinkedList joinedThreadIds = new TLongLinkedList();
        for(TLinkableWrapper<Thread> toBeJoined : threadList) {
            if(toBeJoined.element().isAlive()) {
                try {
                    toBeJoined.element().join();
                    joinedThreadIds.add(toBeJoined.element().getId());

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        lock.lock();
        try {
        ParallelizeActionMultiJoin action = new ParallelizeActionMultiJoin(this, joinedThreadIds, threadJoinedAction.inMethodId(), threadJoinedAction.position());
        runStateMachine.after(new AfterContextForStateMachine(threadJoinedAction.threadLocalDataWhenInTest(),
                    action ),new SendEvent(threadJoinedAction.queueIn(),this));
        waitNotifyStrategy.notifyAndWaitTillActive(threadJoinedAction.threadLocalDataWhenInTest(),
                    runStateMachine,
                    threadActiveCondition,
                    new SendEvent(threadJoinedAction.queueIn(),this));
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void beforeLockExitWaitOrThreadStart(ExecuteBeforeEvent lockExitOrWaitEvent,
                                                ThreadLocalWhenInTest threadLocalDataWhenInTest,
                                                QueueIn queueIn) {
        lock.lock();
        try {
            lockExitOrWaitEvent.setRunId(runId);
            lockExitOrWaitEvent.setLoopId(loopId);
            runStateMachine.beforeLockExitWaitOrThreadStart(lockExitOrWaitEvent,
                    threadLocalDataWhenInTest,
                    new SendEvent(queueIn,this));
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void afterLockExitWaitOrThreadStart(ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {
        lock.lock();
        try {
            runStateMachine.afterLockExitWaitOrThreadStart(threadLocalDataWhenInTest);
            waitNotifyStrategy.notifyAndWaitTillActive(threadLocalDataWhenInTest,
                        runStateMachine,
                        threadActiveCondition,
                        new SendEvent(queueIn,this));

        } finally {
            lock.unlock();
        }
    }

    @Override
    public int runId() {
        return runId;
    }

    @Override
    public int loopId() {
        return loopId;
    }

    @Override
    public void checkAllThreadsJoined() {
        lock.lock();
        try {
            threadPoolMap.checkAllThreadsJoined();
        } finally {
            lock.unlock();
        }
    }
}
