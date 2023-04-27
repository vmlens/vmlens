package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIdToElementList;

public class ParallelizeTestBuilder {

    public static final int MAIN_THREAD_INDEX = 0;
    public static final int FIRST_WORKER_THREAD_INDEX = 1;

    private final int firstVolatileFieldId = 1;
    private final ThreadIdToElementList<ActionForTest> actualRun = new ThreadIdToElementList<>();

    private ThreadTestBuilder threadTestBuilder;

    public void beginThread(int threadIndex) {
        threadTestBuilder = new ThreadTestBuilder(actualRun,threadIndex);

    }

    public Position startThread(int startedThreadIndex) {
        return threadTestBuilder.startThread(startedThreadIndex);
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
