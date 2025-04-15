package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.warning.LogLevelSingleton;
import com.vmlens.trace.agent.bootstrap.event.warning.LoopWarningEvent;
import com.vmlens.trace.agent.bootstrap.event.warning.Warning;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.*;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestAndSerializableEvents;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static com.vmlens.trace.agent.bootstrap.exception.Message.TEST_BLOCKED_MESSAGE;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class RunImpl implements Run {
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
            runStateMachine.after(afterContext,SendEvent.create(afterContext,this));
            if(afterContext.runtimeEvent().isInterleaveActionFactory()) {
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
    public void newTestTaskStarted(RunnableOrThreadWrapper newWrapper) {
        lock.lock();
        try {
             runStateMachine.newTestTaskStarted(newWrapper);
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


}
