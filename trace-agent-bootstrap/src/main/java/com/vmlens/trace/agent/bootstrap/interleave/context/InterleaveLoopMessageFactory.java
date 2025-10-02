package com.vmlens.trace.agent.bootstrap.interleave.context;

public interface InterleaveLoopMessageFactory {

    void maximumAlternatingOrdersCapped(int actual);
    void maximumIterationsReached();

}
