package com.vmlens.trace.agent.bootstrap.ucTest;


import com.vmlens.trace.agent.bootstrap.callback.field.FieldAccessCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.field.GetOrCreateObjectState;
import com.vmlens.trace.agent.bootstrap.callback.field.ObjectState;
import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelizeProvider;
import com.vmlens.trace.agent.bootstrap.testFixture.InitialRunMockFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FieldAccessCallbackImplTestFactory {

    public FieldAccessCallbackImpl create(QueueIn queue) {
        ThreadLocalForParallelize callbackStatePerThreadForParallelize =
                new ThreadLocalForParallelize(1L, queue);

        ThreadLocalDataWhenInTest threadLocalDataWhenInTest =
                new ThreadLocalDataWhenInTest(new InitialRunMockFactory().create(1, 1),
                        1, queue, 1L);
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
