package com.vmlens.trace.agent.bootstrap.parallelize.functionalTest;

import com.vmlens.trace.agent.bootstrap.parallelize.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.parallelize.testFixture.SyncActionVisitor;
import com.vmlens.trace.agent.bootstrap.parallelize.testFixture.VolatileFieldModel;

public class CallParallelizeFacadeSyncActionVisitor implements SyncActionVisitor {

    private final ParallelizeFacade parallelizeFacade;
    private final CallbackStatePerThreadRepository callbackStatePerThreadRepository;

    public CallParallelizeFacadeSyncActionVisitor(ParallelizeFacade parallelizeFacade, CallbackStatePerThreadRepository
            callbackStatePerThreadRepository) {
        this.parallelizeFacade = parallelizeFacade;
        this.callbackStatePerThreadRepository = callbackStatePerThreadRepository;
    }

    @Override
    public void visit(VolatileFieldModel volatileFieldModel) {
        parallelizeFacade.beforeFieldAccessVolatile(callbackStatePerThreadRepository.get(volatileFieldModel.threadIndex)
                , -1l, volatileFieldModel.fieldId,
                volatileFieldModel.operation);
    }
}
