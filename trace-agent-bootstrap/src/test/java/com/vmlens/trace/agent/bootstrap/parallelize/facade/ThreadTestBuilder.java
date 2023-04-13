package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIdToElementList;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.LoopThreadState;

public class ThreadTestBuilder {
    private long defaultObjectHashCode = 1L;
    private final ThreadIdToElementList<ActionForTest> actualRun;
    private final int threadIndex;
    private int position;

    public ThreadTestBuilder(ThreadIdToElementList<ActionForTest> actualRun, int threadIndex) {
        this.actualRun = actualRun;
        this.threadIndex = threadIndex;
    }
    public Position volatileAccess(final int fieldId,final int operation) {
        Position temp =  new Position(threadIndex,position);
        actualRun.add(new ActionForTest(temp){
            @Override
            public void execute(ThreadState loopThreadState) {
                ParallelizeFacade.beforeFieldAccessVolatile(loopThreadState,defaultObjectHashCode,fieldId,operation);
            }
        });
        position++;
        return temp;
    }
}
