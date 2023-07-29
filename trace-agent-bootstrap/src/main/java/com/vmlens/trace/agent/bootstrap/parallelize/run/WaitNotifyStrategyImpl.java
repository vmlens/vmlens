package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AgentLogger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class WaitNotifyStrategyImpl implements WaitNotifyStrategy {

    private final AgentLogger agentLogger;

    public WaitNotifyStrategyImpl(AgentLogger agentLogger) {
        this.agentLogger = agentLogger;
    }

    @Override
    public void notifyAndWaitTillActive(TestThreadState testThreadState, RunStateMachine runStateMachine, Condition threadActiveCondition)
            throws TestBlockedException {
        try {
            threadActiveCondition.signalAll();
            long started = System.currentTimeMillis();
            while (!runStateMachine.isActive(testThreadState)) {
                threadActiveCondition.await(10, TimeUnit.MICROSECONDS);
                if ((System.currentTimeMillis() - started) > 3000) {
                    agentLogger.debug(this.getClass(), "blocked:" + testThreadState.threadId());
                    throw new TestBlockedException();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
