package com.vmlens.trace.agent.bootstrap.inttest.util;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;

import java.util.concurrent.locks.Condition;

public class WaitNotifyStrategyMock implements WaitNotifyStrategy {
    @Override
    public void notifyAndWaitTillActive(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                                        RunStateMachine runStateMachine,
                                        Condition threadActiveCondition,
                                        SendEvent sendEvent) {

    }

    @Override
    public void wakeUpAllThreads(Condition threadActiveCondition) {

    }
}
