package com.vmlens.trace.agent.bootstrap.parallelize.loop;

public class AllInterleavingsRunFactoryImpl implements AllInterleavingsRunFactory {

    private final Object MONITOR = new Object();
    private int maxRunId;

    @Override
    public AllInterleavingsRun create() {
        synchronized (MONITOR) {
            return new AllInterleavingsRunImpl(maxRunId++);
        }

    }
}
