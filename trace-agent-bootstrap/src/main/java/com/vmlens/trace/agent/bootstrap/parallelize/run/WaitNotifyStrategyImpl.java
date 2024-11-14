package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalDataWhenInTest;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AgentLogger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class WaitNotifyStrategyImpl implements WaitNotifyStrategy {

    private final AgentLogger agentLogger;
    private static final long DEFAULT_WAIT_TIME = 5 * 60 * 1000;

    public WaitNotifyStrategyImpl(AgentLogger agentLogger) {
        this.agentLogger = agentLogger;
    }

    @Override
    public void notifyAndWaitTillActive(ThreadLocalDataWhenInTest threadLocalDataWhenInTest, RunStateMachine runStateMachine, Condition threadActiveCondition)
            throws TestBlockedException {
        try {
            threadActiveCondition.signalAll();
            long started = System.currentTimeMillis();
            while (!runStateMachine.isActive(threadLocalDataWhenInTest)) {
                threadActiveCondition.await(10, TimeUnit.MICROSECONDS);
                if ((System.currentTimeMillis() - started) > DEFAULT_WAIT_TIME) {
                    agentLogger.debug(this.getClass(), "blocked:" + threadLocalDataWhenInTest.threadIndex());
                    throw new TestBlockedException();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
