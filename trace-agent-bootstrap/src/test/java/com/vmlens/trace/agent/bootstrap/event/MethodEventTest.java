package com.vmlens.trace.agent.bootstrap.event;

import com.vmlens.trace.agent.bootstrap.callback.RunMock;
import com.vmlens.trace.agent.bootstrap.event.impl.MethodEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.MethodExitEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ActualRunMock;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ActualRunMockStrategyTake;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateActive;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MethodEventTest {

    @Test
    public void methodCounterForEnter() {
        // Given
        MethodEnterEvent methodEnterEventOne = new MethodEnterEvent(6);
        MethodEnterEvent methodEnterEventTwo = new MethodEnterEvent(6);
        ThreadLocalDataWhenInTest threadLocalDataWhenInTest = new ThreadLocalDataWhenInTest(new RunMock(), 0);

        // When
        SerializableEvent resultOne = threadLocalDataWhenInTest.after(methodEnterEventOne);
        SerializableEvent resultTwo = threadLocalDataWhenInTest.after(methodEnterEventTwo);

        // Then
        assertThat(resultOne, CoreMatchers.<SerializableEvent>is(methodEnterEventOne));
        assertThat(methodEnterEventOne.methodCounter(), is(1));
        assertThat(methodEnterEventTwo.methodCounter(), is(2));
    }

    @Test
    public void methodCounterForExit() {
        // Given
        MethodExitEvent methodEventOne = new MethodExitEvent(6);
        MethodExitEvent methodEventTwo = new MethodExitEvent(6);
        ThreadLocalDataWhenInTest threadLocalDataWhenInTest = new ThreadLocalDataWhenInTest(new RunMock(), 0);

        // When
        SerializableEvent resultOne = threadLocalDataWhenInTest.after(methodEventOne);
        SerializableEvent resultTwo = threadLocalDataWhenInTest.after(methodEventTwo);

        // Then
        assertThat(resultOne, CoreMatchers.<SerializableEvent>is(methodEventOne));
        assertThat(methodEventOne.methodCounter(), is(1));
        assertThat(methodEventTwo.methodCounter(), is(2));
    }

    @Test
    public void createNoInterleaveActionForEnter() {
        // Given
        MethodEnterEvent methodEvent = new MethodEnterEvent(6);

        ActualRunMock actualRunMock = new ActualRunMock(new ActualRunMockStrategyTake());
        RunStateActive runStateActive = new RunStateActive(actualRunMock,
                null, null);

        // When
        runStateActive.after(methodEvent, null);

        // Then
        assertThat(actualRunMock.interleaveActions().size(), is(0));
    }

    @Test
    public void createInterleaveActionForExit() {
        // Given
        MethodExitEvent methodEvent = new MethodExitEvent(6);

        ActualRunMock actualRunMock = new ActualRunMock(new ActualRunMockStrategyTake());
        RunStateActive runStateActive = new RunStateActive(actualRunMock,
                null, null);

        // When
        runStateActive.after(methodEvent, null);

        // Then
        assertThat(actualRunMock.interleaveActions().size(), is(0));
    }

}
