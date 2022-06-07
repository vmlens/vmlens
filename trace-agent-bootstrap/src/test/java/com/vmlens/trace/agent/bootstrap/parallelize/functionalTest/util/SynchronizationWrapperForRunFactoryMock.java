package com.vmlens.trace.agent.bootstrap.parallelize.functionalTest.util;

import com.vmlens.trace.agent.bootstrap.Logger;
import com.vmlens.trace.agent.bootstrap.parallelize.runAndLoop.AllInterleavingsLoop;
import com.vmlens.trace.agent.bootstrap.parallelize.runAndLoop.SynchronizationWrapperForRun;
import com.vmlens.trace.agent.bootstrap.parallelize.runAndLoop.SynchronizationWrapperForRunFactory;

public class SynchronizationWrapperForRunFactoryMock implements SynchronizationWrapperForRunFactory {

    private SynchronizationWrapperForRunMock currentRun;

    @Override
    public SynchronizationWrapperForRun create(AllInterleavingsLoop loop) {
        currentRun = new SynchronizationWrapperForRunMock(loop);
        return currentRun;
    }

    public SynchronizationWrapperForRunMock getCurrentRun() {
        return currentRun;
    }

    @Override
    public Logger getLogger() {
        return new TestDebugLogger();
    }
}
