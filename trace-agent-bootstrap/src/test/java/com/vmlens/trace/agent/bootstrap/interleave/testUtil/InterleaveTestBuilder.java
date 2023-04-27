package com.vmlens.trace.agent.bootstrap.interleave.testUtil;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIdToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InterleaveTestBuilder {

    public final int MAIN_THREAD_INDEX = 0;
    public final int FIRST_WORKER_THREAD_INDEX = 1;

    private final int firstVolatileFieldId = 1;
    private final TestBuilderResult testBuilderResult;
    private ThreadTestBuilder threadTestBuilder;

    public InterleaveTestBuilder(TestBuilderResult testBuilderResult) {
        this.testBuilderResult = testBuilderResult;
    }

    public void beginThread(int threadIndex) {
        threadTestBuilder = new ThreadTestBuilder(testBuilderResult,threadIndex);
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

}
