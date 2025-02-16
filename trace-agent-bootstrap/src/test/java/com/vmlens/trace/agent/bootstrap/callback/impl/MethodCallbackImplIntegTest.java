package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodEnterEvent;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.NormalMethodStrategy;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.callback.impl.CallbackTestContainer.TEST_THREAD_INDEX;
import static com.vmlens.trace.agent.bootstrap.callback.impl.runaction.After.after;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MethodCallbackImplIntegTest {

    @Test
    public void normalMethod() {
        // Expected
        PerThreadCounter perThreadCounter = mock(PerThreadCounter.class);
        when(perThreadCounter.incrementAndGetMethodCount()).thenReturn(1);

        int methodId = 5;
        MethodEnterEvent expectedEvent = new MethodEnterEvent(methodId);
        expectedEvent.setThreadIndex(TEST_THREAD_INDEX);
        expectedEvent.setMethodCounter(perThreadCounter);

        // Given
        Object object = new Object();
        CallbackTestContainer callbackTestContainer = CallbackTestContainer.create(true);
        callbackTestContainer.setStrategyAll(methodId, NormalMethodStrategy.SINGLETON);

        // When
        callbackTestContainer.methodCallbackImpl().methodEnter(object, methodId);

        // Then
        assertThat(callbackTestContainer.eventList().size(), is(1));
        assertThat(callbackTestContainer.eventList().get(0), is(expectedEvent));

        assertThat(callbackTestContainer.runActions().size(), is(1));
        assertThat(callbackTestContainer.runActions().get(0), is(after(expectedEvent)));
    }


    @Test
    public void synchronizedMethod() {

    }


    @Test
    public void runMethod() {

    }

    @Test
    public void synchronizedRunMethod() {

    }

}
