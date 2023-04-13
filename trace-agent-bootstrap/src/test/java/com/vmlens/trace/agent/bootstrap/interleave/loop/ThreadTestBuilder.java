package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIdToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.InterleaveActionWithPositionFactoryImpl;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.VolatileFieldAccess;

public class ThreadTestBuilder {
    private final ThreadIdToElementList<InterleaveActionWithPositionFactory> actualRun;
    private final int threadIndex;
    private int position;

    public ThreadTestBuilder(ThreadIdToElementList<InterleaveActionWithPositionFactory> actualRun, int threadIndex) {
        this.actualRun = actualRun;
        this.threadIndex = threadIndex;
    }
    public Position volatileAccess(int fieldId,int operation) {
        Position temp =  new Position(threadIndex,position);
        actualRun.add(new InterleaveActionWithPositionFactoryImpl(new VolatileFieldAccess(fieldId,operation),threadIndex));
        position++;
        return temp;
    }
}
