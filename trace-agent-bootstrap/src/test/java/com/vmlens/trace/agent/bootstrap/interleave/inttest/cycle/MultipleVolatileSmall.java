package com.vmlens.trace.agent.bootstrap.interleave.inttest.cycle;

import com.vmlens.trace.agent.bootstrap.interleave.AbstractInterleaveActionBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.ExpectedBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestOperation;

public class MultipleVolatileSmall extends AbstractInterleaveActionBuilder {

    private final ExpectedBuilder expectedBuilder = new ExpectedBuilder();

    @Override
    protected void addActions() {
        threadStart(0,1);
        threadStart(0,2);
        volatileAccess(2,volatileField(2283,2067180044L),1);
        IntTestOperation firstWrite_2 = volatileAccess(2, volatileField(2283, 2067180044L), 2);
        volatileAccess(2,volatileField(2283,2067180044L),1);
        IntTestOperation lastWrite_2 =  volatileAccess(2,volatileField(2283,2067180044L),2);
        lastActionInThread(2);
        volatileAccess(1,volatileField(2283,2067180044L),1);
        IntTestOperation firstWrite_1 =  volatileAccess(1,volatileField(2283,2067180044L),2);
        volatileAccess(1,volatileField(2283,2067180044L),1);
        IntTestOperation lastWrite_1 = volatileAccess(1,volatileField(2283,2067180044L),2);
        lastActionInThread(1);
        threadJoin(0,1);
        threadJoin(0,2);
        volatileAccess(0,volatileField(3011,1807648168L),1);
        volatileAccess(0,volatileField(2283,2067180044L),1);

        expectedBuilder.group(firstWrite_2,firstWrite_1);
        expectedBuilder.group(firstWrite_1,firstWrite_2);

        expectedBuilder.group(lastWrite_2,lastWrite_1);
        expectedBuilder.group(lastWrite_1,lastWrite_2);
    }

    public ExpectedBuilder expectedBuilder() {
        return expectedBuilder;
    }
}