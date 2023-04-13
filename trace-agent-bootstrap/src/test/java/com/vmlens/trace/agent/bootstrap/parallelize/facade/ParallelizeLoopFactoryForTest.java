package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoop;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;

public class ParallelizeLoopFactoryForTest implements ParallelizeLoopFactory  {

    private RunFactoryForTest currentRunFactoryForTest;
    boolean isActive(int threadIndex) {
        return currentRunFactoryForTest.isActive(threadIndex);
    }

    @Override
    public ParallelizeLoop create(int loopId) {
        currentRunFactoryForTest = new RunFactoryForTest();
        return new ParallelizeLoop(loopId,currentRunFactoryForTest, new InterleaveLoop().iterator());
    }

    public Run getCurrentRun() {
        return currentRunFactoryForTest.getCurrentRun();
    }
}
