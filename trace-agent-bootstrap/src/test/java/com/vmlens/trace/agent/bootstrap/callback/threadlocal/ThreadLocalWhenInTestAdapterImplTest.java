package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ThreadLocalWhenInTestAdapterImplTest {

    @Test
    public void startCallbackProcessing() {
        // Given
        QueueIn queueIn = mock(QueueIn.class);
        ThreadLocalForParallelize threadLocalForParallelize = new ThreadLocalForParallelize(5L, "test");

        ThreadLocalForParallelizeProviderMock threadLocalForParallelizeProviderMock =
                new ThreadLocalForParallelizeProviderMock(threadLocalForParallelize);
        ThreadLocalWhenInTestAdapterImpl threadLocalWhenInTestAdapterImpl =
                new ThreadLocalWhenInTestAdapterImpl(threadLocalForParallelizeProviderMock, queueIn);

        Run runMock = mock(Run.class);
        ThreadLocalWhenInTest threadLocalWhenInTest = new ThreadLocalWhenInTest(runMock, 2);
        threadLocalForParallelize.setThreadLocalDataWhenInTest(threadLocalWhenInTest);

        CallbackAction callbackAction = mock(CallbackAction.class);

        // When
        threadLocalForParallelize.setInCallbackProcessing();
        threadLocalWhenInTestAdapterImpl.process(callbackAction);

        // Then
        verify(callbackAction, never()).execute(threadLocalWhenInTest);

    }


}
