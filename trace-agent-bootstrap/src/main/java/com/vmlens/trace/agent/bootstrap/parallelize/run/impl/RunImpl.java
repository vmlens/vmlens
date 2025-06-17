package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.AfterContext;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.*;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

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
    public void threadStarted(ThreadWrapper newWrapper) {
        lock.lock();
        try {
             runStateMachine.newTestTaskStarted(newWrapper);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void threadStartedByPool(ThreadStartedByPoolContext context) {
        lock.lock();
        try {
            threadPoolMap.add(context);
            runStateMachine.newTestTaskStarted(new ThreadWrapper(context.startedThread()));
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void threadJoinedByPool(JoinAction threadJoinedAction) {
        lock.lock();
        try {
            threadPoolMap.process(threadJoinedAction);
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
