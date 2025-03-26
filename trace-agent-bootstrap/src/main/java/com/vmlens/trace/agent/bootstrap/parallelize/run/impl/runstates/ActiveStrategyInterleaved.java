package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstates;

import com.vmlens.trace.agent.bootstrap.exception.Message;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.exception.TestBlockedException;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public class ActiveStrategyInterleaved implements ActiveStrategy {

    private final CalculatedRun calculatedRun;

    public ActiveStrategyInterleaved(CalculatedRun calculatedRun) {
        this.calculatedRun = calculatedRun;
    }

    @Override
    public boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        return calculatedRun.isActive(threadLocalDataWhenInTest.threadIndex());
    }

    @Override
    public void checkStopWaiting(ThreadIndexAndThreadStateMap runContext) throws TestBlockedException {
        Integer activeThreadIndex = calculatedRun.activeThreadIndex();
        if(activeThreadIndex != null) {
            if(runContext.isBlocked(activeThreadIndex)) {
                System.err.println(calculatedRun);
                new TestBlockedException(Message.TEST_BLOCKED_MESSAGE).printStackTrace();
                throw new TestBlockedException(Message.TEST_BLOCKED_MESSAGE);
            }
        }
    }
}
