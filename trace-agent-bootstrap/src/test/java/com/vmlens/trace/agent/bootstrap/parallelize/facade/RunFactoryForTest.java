package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunFactory;

public class RunFactoryForTest implements RunFactory {

    private CalculatedRun currentCalculatedRun;
    private Run currentRun;

    @Override
    public Run create(int maxRunId, CalculatedRun calculatedRun) {
        currentCalculatedRun = calculatedRun;
        currentRun =  new RunWrapperForTest(currentCalculatedRun);
        return currentRun;
    }

    public Run getCurrentRun() {
        return currentRun;
    }

    public boolean isActive(int threadIndex) {
        return currentCalculatedRun.isActive(threadIndex);
    }

}
