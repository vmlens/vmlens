package com.vmlens.trace.agent.bootstrap.interleave.loop.volatilereadwrite;

import com.vmlens.trace.agent.bootstrap.interleave.AbstractInterleaveActionBuilder;

public class DifferentHashCodeB extends AbstractInterleaveActionBuilder {
    @Override
    protected void addActions() {
        threadStart(0,1);
        volatileAccess(0,volatileField(3175,76659128L),1);
        volatileAccess(1,volatileField(3175,76659128L),1);
        volatileAccess(1,volatileField(3175,76659128L),2);
        volatileAccess(0,volatileField(3175,76659128L),2);
        threadJoin(0,1);
        volatileAccess(0,volatileField(3175,76659128L),1);
    }
}
