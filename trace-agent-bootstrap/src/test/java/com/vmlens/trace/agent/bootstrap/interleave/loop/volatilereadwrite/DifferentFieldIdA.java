package com.vmlens.trace.agent.bootstrap.interleave.loop.volatilereadwrite;

import com.vmlens.trace.agent.bootstrap.interleave.AbstractInterleaveActionBuilder;

public class DifferentFieldIdA  extends AbstractInterleaveActionBuilder {
    @Override
    protected void addActions() {
        threadStart(0,1);
        volatileAccess(0,volatileField(2458,1383178166L),2);
        volatileAccess(1,volatileField(2458,1383178166L),2);
        threadJoin(0,1);
        volatileAccess(0,volatileField(2458,1383178166L),1);
    }
}
