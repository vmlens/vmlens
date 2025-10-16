package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

public interface CycleFilter {

    void addLeftBeforeRight(Position left, Position right, int cycleId);

    int nextCycleId();
    void setNextCycleId(int next);
}
