package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoop;

public class ObjectAndParallelizeLoop {

    private final Object object;
    private final ParallelizeLoop loop;

    public ObjectAndParallelizeLoop(Object object, ParallelizeLoop loop) {
        this.object = object;
        this.loop = loop;
    }

    public Object object() {
        return object;
    }

    public ParallelizeLoop loop() {
        return loop;
    }
}
