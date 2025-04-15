package com.vmlens.trace.agent.bootstrap.inttest;

import com.vmlens.trace.agent.bootstrap.inttest.util.CallbackTestContainer;
import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodExitEvent;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.NormalMethodStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.RunMethodStrategy;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.inttest.util.CallbackTestContainer.*;
import static com.vmlens.trace.agent.bootstrap.inttest.util.CallbackTestContainer.RUN_ID;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MethodCallbackImplIntTest {

    private static PerThreadCounter perThreadCounter(int counter) {
        PerThreadCounter perThreadCounter = mock(PerThreadCounter.class);
        when(perThreadCounter.incrementAndGetMethodCount()).thenReturn(counter);
        return perThreadCounter;
    }

    @Test
    public void normalMethod() {
        // Expected
        int methodId = 5;
        MethodEnterEvent methodEnter = new MethodEnterEvent(methodId);
        methodEnter.setThreadIndex(TEST_THREAD_INDEX);
        methodEnter.setMethodCounter(perThreadCounter(1));
        methodEnter.setLoopId(LOOP_ID);
        methodEnter.setRunId(RUN_ID);

        MethodExitEvent methodExit = new MethodExitEvent(methodId);
        methodExit.setThreadIndex(TEST_THREAD_INDEX);
        methodExit.setMethodCounter(perThreadCounter(2));
        methodExit.setLoopId(LOOP_ID);
        methodExit.setRunId(RUN_ID);

        // Given
        Object object = new Object();
        CallbackTestContainer callbackTestContainer = CallbackTestContainer.create();
        callbackTestContainer.setStrategyAll(methodId, NormalMethodStrategy.SINGLETON);

        // When
        callbackTestContainer.methodCallbackImpl().methodEnter(object, methodId);
        callbackTestContainer.methodCallbackImpl().methodExit(object, methodId);

        // Then
        assertThat(callbackTestContainer.eventList().size(), is(2));
        assertThat(callbackTestContainer.eventList().get(0), is(methodEnter));
        assertThat(callbackTestContainer.eventList().get(1), is(methodExit));
    }

    @Test
    public void runMethod() {
        // Expected
        int methodId = 5;
        MethodEnterEvent methodEnter = new MethodEnterEvent(methodId);
        methodEnter.setThreadIndex(TEST_THREAD_INDEX);
        methodEnter.setMethodCounter(perThreadCounter(1));
        methodEnter.setLoopId(LOOP_ID);
        methodEnter.setRunId(RUN_ID);

        // Given
        Object object = new Object();
        CallbackTestContainer callbackTestContainer = CallbackTestContainer.create();
        callbackTestContainer.setStrategyAll(methodId, new RunMethodStrategy(NormalMethodStrategy.SINGLETON));

        // When
        callbackTestContainer.methodCallbackImpl().methodEnter(object, methodId);

        // Then
        assertThat(callbackTestContainer.eventList().size(), is(1));
        assertThat(callbackTestContainer.eventList().get(0), is(methodEnter));
    }

}
