package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestBlockedException;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class WaitNotifyStrategyImpl implements WaitNotifyStrategy {


    private static final long DEFAULT_WAIT_TIME = 5 * 60 * 1000;

    public WaitNotifyStrategyImpl() {
    }

    @Override
    public void notifyAndWaitTillActive(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                                        RunStateMachine runStateMachine,
                                        Condition threadActiveCondition)
            throws TestBlockedException {
        try {
            threadActiveCondition.signalAll();
            long started = System.currentTimeMillis();
            while (!runStateMachine.canProcessEndOfOperation(threadLocalDataWhenInTest)) {
                threadActiveCondition.await(100, TimeUnit.MICROSECONDS);
                runStateMachine.checkStopWaiting();
                if ((System.currentTimeMillis() - started) > DEFAULT_WAIT_TIME) {
                    throw new TestBlockedException();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void waitForCanStartAtomicOperation(RunStateMachine runStateMachine, Condition threadActiveCondition) throws TestBlockedException {
        try {
            long started = System.currentTimeMillis();
            while (!runStateMachine.canStartAtomicOperation()) {
                threadActiveCondition.await(100, TimeUnit.MICROSECONDS);
                runStateMachine.checkStopWaiting();
                if ((System.currentTimeMillis() - started) > DEFAULT_WAIT_TIME) {
                    throw new TestBlockedException();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
