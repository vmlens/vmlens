package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIdToElementList;

public class ParallelizeTestBuilder {

    private final int firstVolatileFieldId = 1;
    private final ThreadIdToElementList<ActionForTest> actualRun = new ThreadIdToElementList<>();
    private int threadIndex = 0;
    private ThreadTestBuilder threadTestBuilder;

    public void beginMainThread() {
        threadTestBuilder = new ThreadTestBuilder(actualRun,threadIndex);
        threadIndex++;
    }

    public void beginFirstWorkerThread() {
        threadTestBuilder = new ThreadTestBuilder(actualRun,threadIndex);
        threadIndex++;
    }

    public Position readFirstVolatileField() {
        return threadTestBuilder.volatileAccess(firstVolatileFieldId,MemoryAccessType.IS_READ);
    }

    public Position writeFirstVolatileField() {
        return threadTestBuilder.volatileAccess(firstVolatileFieldId,MemoryAccessType.IS_WRITE);
    }

    public ThreadIdToElementList<ActionForTest> build() {
        return actualRun;
    }


}
