package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.exception.Message;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

import static com.vmlens.trace.agent.bootstrap.exception.Message.TEST_BLOCKED_MESSAGE;

public class WaitNotifyStrategyImpl implements WaitNotifyStrategy {


    private static final long DEFAULT_WAIT_TIME = 5 * 60 * 1000;

    public WaitNotifyStrategyImpl() {
    }

    @Override
    public void notifyAndWaitTillActive(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                                        RunStateMachine runStateMachine,
                                        Condition threadActiveCondition,
                                        SendEvent sendEvent) {
        try {
            threadActiveCondition.signalAll();
            while (!runStateMachine.isActive(threadLocalDataWhenInTest)) {
                threadActiveCondition.await(100, TimeUnit.MICROSECONDS);
                if(runStateMachine.checkStopWaiting()) {
                    sendEvent.sendMessage(TEST_BLOCKED_MESSAGE);
                    return;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


}
