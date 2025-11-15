package com.vmlens.trace.agent.bootstrap.interleave;

import com.vmlens.trace.agent.bootstrap.Pair;

public interface CheckMinimumPositon {

    void addLeftAfterRight(LeftAfterRight leftAfterRight);
    Pair<LeftBeforeRight,LeftBeforeRight> checkLeftBeforeRight(LeftBeforeRight leftBeforeRight);


}
