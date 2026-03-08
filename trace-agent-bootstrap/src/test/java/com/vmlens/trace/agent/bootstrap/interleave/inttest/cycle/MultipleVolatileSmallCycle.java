package com.vmlens.trace.agent.bootstrap.interleave.inttest.cycle;

import com.vmlens.trace.agent.bootstrap.interleave.AbstractInterleaveActionBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.ExpectedBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestOperation;

public class MultipleVolatileSmallCycle extends AbstractInterleaveActionBuilder {

    private final ExpectedBuilder expectedBuilder = new ExpectedBuilder();

    @Override
    protected void addActions() {
        threadStart(0,1);
        IntTestOperation firstReadOne = volatileAccess(0, volatileField(3276, 1506856374L), 1);
        volatileAccess(0,volatileField(3276,1506856374L),2);
        volatileAccess(0,volatileField(3276,1506856374L),1);
        IntTestOperation lastWriteOne = volatileAccess(0,volatileField(3276,1506856374L),2);
        IntTestOperation firstReadSecond = volatileAccess(1,volatileField(3276,1506856374L),1);
        IntTestOperation firstWriteSecond =  volatileAccess(1,volatileField(3276,1506856374L),2);
        volatileAccess(1,volatileField(3276,1506856374L),1);
        volatileAccess(1,volatileField(3276,1506856374L),2);
        lastActionInThread(1);
        threadJoin(0,1);
        expectedBuilder.group(firstReadOne,firstReadSecond,lastWriteOne,firstWriteSecond);
    }

    public ExpectedBuilder expectedBuilder() {
        return expectedBuilder;
    }
}