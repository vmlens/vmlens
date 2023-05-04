package com.vmlens.trace.agent.bootstrap.interleave.testUtil;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InterleaveTestBuilder {

    public final int MAIN_THREAD_INDEX = 0;
    public final int FIRST_WORKER_THREAD_INDEX = 1;

    private final int firstVolatileFieldId = 1;
    private final ResultTestBuilder resultTestBuilder;
    private ThreadTestBuilder threadTestBuilder;

    public InterleaveTestBuilder(ResultTestBuilder resultTestBuilder) {
        this.resultTestBuilder = resultTestBuilder;
    }

    public void beginThread(int threadIndex) {
        threadTestBuilder = new ThreadTestBuilder(resultTestBuilder,threadIndex);
    }

    public Position readFirstVolatileField() {
        return threadTestBuilder.volatileAccess(firstVolatileFieldId,MemoryAccessType.IS_READ);
    }

    public Position writeFirstVolatileField() {
        return threadTestBuilder.volatileAccess(firstVolatileFieldId,MemoryAccessType.IS_WRITE);
    }

    public Position startThread(int index) {
        return threadTestBuilder.startThread(index);
    }

    public Position joinThread(int index) {
        return threadTestBuilder.joinThread(index);
    }
}
