package com.vmlens.trace.agent.bootstrap.interleave;

public interface CheckMinimumPositon {

    void setIfGreater(Position position);
    int get(int threadIndex);

}
