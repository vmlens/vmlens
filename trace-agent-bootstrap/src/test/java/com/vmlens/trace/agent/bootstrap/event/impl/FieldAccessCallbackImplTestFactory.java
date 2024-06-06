package com.vmlens.trace.agent.bootstrap.event.impl;


import com.vmlens.trace.agent.bootstrap.callback.field.FieldAccessCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.field.GetOrCreateObjectState;
import com.vmlens.trace.agent.bootstrap.callback.field.ObjectState;
import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelizeProvider;
import com.vmlens.trace.agent.bootstrap.testfixture.RunInitialTestFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FieldAccessCallbackImplTestFactory {

    public FieldAccessCallbackImpl create(QueueIn queue, int loopId, int runId, long threadId) {
        ThreadLocalForParallelize callbackStatePerThreadForParallelize =
                new ThreadLocalForParallelize(1L, queue);

        ThreadLocalDataWhenInTest threadLocalDataWhenInTest =
                new ThreadLocalDataWhenInTest(new RunInitialTestFactory().create(loopId, runId),
                        1, queue, threadId);
        callbackStatePerThreadForParallelize.setThreadLocalDataWhenInTest(threadLocalDataWhenInTest);


        ObjectState objectState = new ObjectState();
        GetOrCreateObjectState getAndUpdateVolatileObjectState = mock(GetOrCreateObjectState.class);
        when(getAndUpdateVolatileObjectState.getOrCreate(any())).thenReturn(objectState);


        ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider = mock(ThreadLocalForParallelizeProvider.class);
        when(threadLocalForParallelizeProvider.getThreadLocalForParallelize())
                .thenReturn(callbackStatePerThreadForParallelize);

        return new FieldAccessCallbackImpl(getAndUpdateVolatileObjectState, threadLocalForParallelizeProvider);
    }

}
