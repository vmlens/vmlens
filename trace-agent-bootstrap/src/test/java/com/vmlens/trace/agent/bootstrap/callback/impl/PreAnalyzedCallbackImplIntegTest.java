package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.ThreadStartStrategy;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.callback.impl.CallbackTestContainer.TEST_THREAD_INDEX;
import static com.vmlens.trace.agent.bootstrap.callback.impl.runaction.EndAtomicOperation.endAtomicOperation;
import static com.vmlens.trace.agent.bootstrap.callback.impl.runaction.StartAtomicOperationWithNewThread.startAtomicOperationWithNewThread;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PreAnalyzedCallbackImplIntegTest {

    @Test
    public void threadStart() {
        // Expected
        int inMethodId = 97;
        int position = 2;
        int methodId = 5;
        Object object = new Object();
        RunnableOrThreadWrapper runnableOrThreadWrapper = new RunnableOrThreadWrapper(object);
        ThreadStartEvent threadStartEvent = new ThreadStartEvent();
        threadStartEvent.setThreadIndex(TEST_THREAD_INDEX);

        threadStartEvent.setInMethodIdAndPosition(inMethodId, position);

        // Given
        CallbackTestContainer callbackTestContainer = CallbackTestContainer.create(true);
        callbackTestContainer.setStrategyPreAnalyzed(methodId, ThreadStartStrategy.SINGLETON);

        // When
        callbackTestContainer.methodCallbackImpl().beforeMethodCall(inMethodId, position);
        callbackTestContainer.preAnalyzedCallbackImpl().methodEnter(object, methodId);
        callbackTestContainer.preAnalyzedCallbackImpl().methodExit(object, methodId);
        callbackTestContainer.methodCallbackImpl().afterMethodCall(inMethodId, position);

        // Then
        assertThat(callbackTestContainer.runActions().size(), is(2));
        assertThat(callbackTestContainer.runActions().get(0), is(
                startAtomicOperationWithNewThread(runnableOrThreadWrapper)));
        assertThat(callbackTestContainer.runActions().get(1), is(
                endAtomicOperation(threadStartEvent)));

    }
}
