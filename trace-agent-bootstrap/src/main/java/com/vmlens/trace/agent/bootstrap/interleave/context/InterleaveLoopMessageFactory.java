package com.vmlens.trace.agent.bootstrap.interleave.context;

public interface InterleaveLoopMessageFactory {

    void cyclesRemoved(int actual);
    void maximumIterationsReached(int orderTreeLength);

}
