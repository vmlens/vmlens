package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoop;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopRepository;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ParallelizeFacadeTest {

    @Test
    public void removeAfterNewLoop() {
        // Given
        ParallelizeLoopFactory factory = mock(ParallelizeLoopFactory.class);
        when(factory.create(anyInt(),any())).thenReturn(mock(ParallelizeLoop.class));

        ParallelizeFacade facade = new ParallelizeFacade(new ParallelizeLoopRepository(factory));

        ThreadLocalForParallelize threadLocalWrapperForParallelize = mock(ThreadLocalForParallelize.class);
        Object first = new Object();

        // When
        facade.hasNext(threadLocalWrapperForParallelize, first);
        facade.hasNext(threadLocalWrapperForParallelize, new Object());
        facade.hasNext(threadLocalWrapperForParallelize, first);

        // Then
        verify(factory,times(3)).create(anyInt(),any());
    }


}
