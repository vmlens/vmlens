package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;

import java.util.concurrent.locks.Condition;

public class WaitNotifyStrategyImpl implements WaitNotifyStrategy {

    public WaitNotifyStrategyImpl() {
    }



    @Override
    public void notifyAndWaitTillActive(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                                        RunStateMachine runStateMachine,
                                        Condition threadActiveCondition,
                                        SendEvent sendEvent) {
        try {
            threadActiveCondition.signalAll();
            while (!runStateMachine.isActive(threadLocalDataWhenInTest,sendEvent)) {
                threadActiveCondition.await();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void wakeUpAllThreads(Condition threadActiveCondition) {
        threadActiveCondition.signalAll();
    }
}
