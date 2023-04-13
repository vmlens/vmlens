package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.interleave.block.InterleaveActionWithPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIdToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InterleaveTestBuilder {

    private final int firstVolatileFieldId = 1;
    private final ThreadIdToElementList<InterleaveActionWithPositionFactory> actualRun = new ThreadIdToElementList<>();
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

    public ThreadIdToElementList<InterleaveActionWithPositionFactory> build() {
        return actualRun;
    }

    public TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> buildAsList() {
        TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> result = new TLinkedList<>();
        while (!actualRun.isEmpty()) {
            for (int i = 0; i <= actualRun.maxThreadIndex(); i++) {
                while (!actualRun.isEmptyAtIndex(i)) {
                     result.add( new TLinkableWrapper<InterleaveActionWithPositionFactory>(actualRun.getAndRemoveAtIndex(i)));
                    }
                }
            }
        return result;
    }

}
