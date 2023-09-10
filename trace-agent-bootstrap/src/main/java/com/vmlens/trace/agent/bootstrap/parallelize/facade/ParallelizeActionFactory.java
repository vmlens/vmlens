package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.VolatileFieldAccess;
import com.vmlens.trace.agent.bootstrap.parallelize.actionImpl.InterleaveActionWithPositionFactoryFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.actionImpl.ThreadJoin;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;

public class ParallelizeActionFactory {

    public ParallelizeAction threadJoin(long threadId) {
        return new ThreadJoin(threadId);
    }

    public ParallelizeAction fieldAccess(int fieldId, int operation) {
        return new InterleaveActionWithPositionFactoryFactory(
                new VolatileFieldAccess(fieldId, operation));
    }


}
