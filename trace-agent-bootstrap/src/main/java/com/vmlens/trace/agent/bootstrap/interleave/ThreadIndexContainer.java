package com.vmlens.trace.agent.bootstrap.interleave;

public interface ThreadIndexContainer {

    boolean exists(int threadIndex);

    boolean hasMoreThanOneThread();

}
