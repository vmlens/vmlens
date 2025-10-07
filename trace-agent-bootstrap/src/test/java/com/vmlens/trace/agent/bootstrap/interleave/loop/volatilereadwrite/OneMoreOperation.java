package com.vmlens.trace.agent.bootstrap.interleave.loop.volatilereadwrite;

import com.vmlens.trace.agent.bootstrap.interleave.AbstractInterleaveActionBuilder;

public class OneMoreOperation extends AbstractInterleaveActionBuilder {
    @Override
    protected void addActions() {
        threadStart(0,1);
        volatileAccess(0,volatileField(3175,1440621772L),1);
        volatileAccess(0,volatileField(3175,1440621772L),2);
        volatileAccess(1,volatileField(3175,1440621772L),1);
        volatileAccess(1,volatileField(3175,1440621772L),1);
        volatileAccess(1,volatileField(3175,1440621772L),2);
        threadJoin(0,1);
        volatileAccess(0,volatileField(3175,1440621772L),1);
    }
}
