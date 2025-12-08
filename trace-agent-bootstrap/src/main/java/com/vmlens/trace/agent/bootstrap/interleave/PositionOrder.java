package com.vmlens.trace.agent.bootstrap.interleave;

import com.vmlens.trace.agent.bootstrap.Pair;

public interface PositionOrder {

    Pair<LeftBeforeRight,LeftBeforeRight> checkHasCycleOrSetMinimum(CheckMinimumPositon checkMinimumPositon);
    Position left();
    boolean isAfter();

}
