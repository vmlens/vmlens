package com.vmlens.trace.agent.bootstrap.parallelize.functionalTest.util;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.parallelize.testFixture.SyncActionVisitor;
import com.vmlens.trace.agent.bootstrap.parallelize.testFixture.VolatileFieldModel;

public class CallParallelizeFacadeSyncActionVisitor implements SyncActionVisitor {

    private final ParallelizeFacade parallelizeFacade;
    private final CallbackStatePerThreadRepository callbackStatePerThreadRepository;
    private final SynchronizationWrapperForRunFactoryMock allInterleavingsRunFactoryMock;

    public CallParallelizeFacadeSyncActionVisitor(ParallelizeFacade parallelizeFacade,
                                                  CallbackStatePerThreadRepository callbackStatePerThreadRepository,
                                                  SynchronizationWrapperForRunFactoryMock allInterleavingsRunFactoryMock) {
        this.parallelizeFacade = parallelizeFacade;
        this.callbackStatePerThreadRepository = callbackStatePerThreadRepository;
        this.allInterleavingsRunFactoryMock = allInterleavingsRunFactoryMock;
    }

    @Override
    public void visit(VolatileFieldModel volatileFieldModel) {
        CallbackStatePerThread thread = callbackStatePerThreadRepository.get(volatileFieldModel.threadIndex);
        parallelizeFacade.beforeFieldAccessVolatile(thread, -1l, volatileFieldModel.fieldId,
                volatileFieldModel.operation);
        thread.parallelizedThreadLocal.afterFieldAccess();
    }
}
