package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.warning.LogLevelSingleton;
import com.vmlens.trace.agent.bootstrap.event.warning.LoopWarningEvent;
import com.vmlens.trace.agent.bootstrap.event.warning.Warning;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.*;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static com.vmlens.trace.agent.bootstrap.event.warning.Message.TEST_BLOCKED_MESSAGE;
import static com.vmlens.trace.agent.bootstrap.parallelize.run.RuntimeEventAndWarnings.of;

public class RunImpl implements Run {
    private final ReentrantLock lock;
    private final Condition threadActiveCondition;
    private final WaitNotifyStrategy waitNotifyStrategy;
    private final RunStateMachine runStateMachine;
    private final int loopId;
    private final int runId;

    public RunImpl(ReentrantLock lock, WaitNotifyStrategy waitNotifyStrategy,
                   RunStateMachine runStateMachine, int loopId, int runId) {
        this.waitNotifyStrategy = waitNotifyStrategy;
        this.runStateMachine = runStateMachine;
        this.lock = lock;
        this.threadActiveCondition = lock.newCondition();
        this.loopId = loopId;
        this.runId = runId;
    }

    public RuntimeEventAndWarnings after(RuntimeEvent runtimeEvent, ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        lock.lock();
        try {
            runtimeEvent.setLoopId(loopId);
            runtimeEvent.setRunId(runId);
            RuntimeEvent result = runStateMachine.after(runtimeEvent, threadLocalDataWhenInTest);
            if (runtimeEvent.isInterleaveActionFactory()) {
                try {
                    waitNotifyStrategy.notifyAndWaitTillActive(threadLocalDataWhenInTest, runStateMachine, threadActiveCondition);
                } catch (TestBlockedException e) {
                    if (LogLevelSingleton.logLevel().isInfoEnabled()) {
                        Warning warning = new LoopWarningEvent(loopId, runId, TEST_BLOCKED_MESSAGE.id());
                        return of(result, warning);
                    }
                }
            }
            return of(result);
        } finally {
            lock.unlock();
        }
    }

    public RuntimeEventAndWarnings endAtomicOperation(RuntimeEvent runtimeEvent, ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        lock.lock();
        try {
            runtimeEvent.setLoopId(loopId);
            runtimeEvent.setRunId(runId);
            RuntimeEvent result = runStateMachine.endAtomicOperation(runtimeEvent, threadLocalDataWhenInTest);
            if (runtimeEvent.isInterleaveActionFactory()) {
                try {
                    waitNotifyStrategy.notifyAndWaitTillActive(threadLocalDataWhenInTest, runStateMachine, threadActiveCondition);
                } catch (TestBlockedException e) {
                    if (LogLevelSingleton.logLevel().isInfoEnabled()) {
                        Warning warning = new LoopWarningEvent(loopId, runId, TEST_BLOCKED_MESSAGE.id());
                        return of(result, warning);
                    }
                }
            }
            return of(result);
        } finally {
            lock.unlock();
        }
    }


    public void newTask(RunnableOrThreadWrapper newWrapper, ThreadLocalForParallelize threadLocalForParallelize) {
        lock.lock();
        try {
            ThreadLocalWhenInTest threadLocalDataWhenInTest = runStateMachine.processNewTestTask(newWrapper, threadLocalForParallelize, this);
            if (threadLocalDataWhenInTest != null) {
                try {
                    waitNotifyStrategy.notifyAndWaitTillActive(threadLocalDataWhenInTest, runStateMachine, threadActiveCondition);
                } catch (TestBlockedException e) {

                }
            }
        } finally {
            lock.unlock();
        }
    }

    public ActualRun end(ThreadLocalForParallelize threadLocalForParallelize) {
        lock.lock();
        try {
            return runStateMachine.end(threadLocalForParallelize);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int runId() {
        return runId;
    }

    @Override
    public void startAtomicOperation(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        lock.lock();
        try {
            try {
                waitNotifyStrategy.waitForCanStartAtomicOperation(runStateMachine, threadActiveCondition);
            } catch (TestBlockedException e) {
                e.printStackTrace();
            }
            runStateMachine.startAtomicOperation(threadLocalDataWhenInTest);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void startAtomicOperationWithNewThread(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest, RunnableOrThreadWrapper newThread) {
        lock.lock();
        try {
            try {
                waitNotifyStrategy.waitForCanStartAtomicOperation(runStateMachine, threadActiveCondition);
            } catch (TestBlockedException e) {
                e.printStackTrace();
            }
            runStateMachine.startAtomicOperationWithNewThread(threadLocalDataWhenInTest, newThread);
        } finally {
            lock.unlock();
        }
    }
}
