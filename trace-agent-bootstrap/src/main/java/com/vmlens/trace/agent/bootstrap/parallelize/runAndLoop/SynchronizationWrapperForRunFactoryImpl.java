package com.vmlens.trace.agent.bootstrap.parallelize.runAndLoop;

import com.vmlens.trace.agent.bootstrap.Logger;

public class SynchronizationWrapperForRunFactoryImpl implements SynchronizationWrapperForRunFactory {

    private final Object MONITOR = new Object();
    private int maxRunId;

    // ToDo
    @Override
    public SynchronizationWrapperForRun create(AllInterleavingsLoop loop) {
        synchronized (MONITOR) {
            return null; // new SynchronizationWrapperForRunImpl(maxRunId++);
        }
    }

    // ToDo
    @Override
    public Logger getLogger() {
        return null;
    }
}
