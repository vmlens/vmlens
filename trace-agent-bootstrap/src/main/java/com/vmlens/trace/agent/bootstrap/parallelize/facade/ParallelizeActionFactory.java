package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.VolatileFieldAccess;
import com.vmlens.trace.agent.bootstrap.parallelize.actionImpl.ParallelizeActionWithInterleaveAction;
import com.vmlens.trace.agent.bootstrap.parallelize.actionImpl.ParallelizeActionWithInterleaveActionAndOrRuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.actionImpl.ThreadJoin;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;

public class ParallelizeActionFactory {

    private static final ParallelizeActionFactory factory = new ParallelizeActionFactory();

    public static ParallelizeActionFactory action() {
        return factory;
    }

    public ParallelizeAction threadJoin(long threadId) {
        return new ThreadJoin(threadId);
    }

    public ParallelizeAction fieldAccess(int fieldId, int operation) {
        return new ParallelizeActionWithInterleaveAction(
                new VolatileFieldAccess(fieldId, operation));
    }


}
