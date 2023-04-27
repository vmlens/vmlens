package com.vmlens.trace.agent.bootstrap.interleave.testUtil;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIdToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.InterleaveActionWithPositionFactoryImpl;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.VolatileFieldAccess;

public class ThreadTestBuilder {
    private final TestBuilderResult testBuilderResult;
    private final int threadIndex;
    private int position;

    public ThreadTestBuilder(TestBuilderResult testBuilderResult, int threadIndex) {
        this.testBuilderResult = testBuilderResult;
        this.threadIndex = threadIndex;
    }

    public Position volatileAccess(int fieldId, int operation) {
        Position temp =  new Position(threadIndex,position);
        testBuilderResult.add(new VolatileFieldAccess(fieldId,operation),temp);
        position++;
        return temp;
    }
    public Position startThread(int index) {
        Position temp =  new Position(threadIndex,position);
        testBuilderResult.startThread(index,temp);
        position++;
        return temp;
    }

}
