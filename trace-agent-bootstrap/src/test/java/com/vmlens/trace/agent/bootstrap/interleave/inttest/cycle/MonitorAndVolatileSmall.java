package com.vmlens.trace.agent.bootstrap.interleave.inttest.cycle;

import com.vmlens.trace.agent.bootstrap.interleave.AbstractInterleaveActionBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.ExpectedBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestOperation;

public class MonitorAndVolatileSmall extends AbstractInterleaveActionBuilder {

    private final ExpectedBuilder expectedBuilder = new ExpectedBuilder();

    @Override
    protected void addActions() {
        threadStart(0,1);
        threadStart(0,2);
        IntTestOperation enter_2 = lockEnter(2,monitor(2010346894L));
        volatileAccess(2,volatileField(2284,1466785259L),1);
        volatileAccess(2,volatileField(2284,1466785259L),2);
        IntTestOperation exit_2 = lockExit(2,monitor(2010346894L));
        lastActionInThread(2);
        IntTestOperation enter_1 = lockEnter(1,monitor(2010346894L));
        volatileAccess(1,volatileField(2284,1466785259L),1);
        volatileAccess(1,volatileField(2284,1466785259L),2);
        IntTestOperation exit_1 = lockExit(1,monitor(2010346894L));
        lastActionInThread(1);
        threadJoin(0,1);
        threadJoin(0,2);
        volatileAccess(0,volatileField(3012,990897274L),1);
        expectedBuilder.group(exit_1,enter_2);
        expectedBuilder.group(enter_1,exit_2);
    }

    public ExpectedBuilder expectedBuilder() {
        return expectedBuilder;
    }
}
