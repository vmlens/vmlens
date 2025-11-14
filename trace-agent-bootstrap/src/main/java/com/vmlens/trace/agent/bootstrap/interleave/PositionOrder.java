package com.vmlens.trace.agent.bootstrap.interleave;

public interface PositionOrder {

    boolean checkHasCycleOrSetMinimum(CheckMinimumPositon checkMinimumPositon);
    Position left();
    boolean isAfter();

}
