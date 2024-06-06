package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RunStateMachineImplIsActiveTest {
    @Test
    public void testInitial() {
        // Given
        RunStateMachineImplTestFixture fixture = RunStateMachineImplTestFixture.createInitial();

        // Then
        assertThat(fixture.runStateMachine().isActive(fixture.mainTestThread()), is(true));
    }

    @Test
    public void testRunningFalseGetsForwarded() {
        // Given
        CalculatedRun calculatedRunMock = mock(CalculatedRun.class);
        when(calculatedRunMock.isActive(anyInt())).thenReturn(false);
        RunStateMachineImplTestFixture fixture = RunStateMachineImplTestFixture.createRunning(calculatedRunMock);

        // Then
        assertThat(fixture.runStateMachine().isActive(fixture.mainTestThread()), is(false));
    }

    @Test
    public void testRunningTrueGetsForwarded() {
        // Given
        CalculatedRun calculatedRunMock = mock(CalculatedRun.class);
        when(calculatedRunMock.isActive(anyInt())).thenReturn(true);
        RunStateMachineImplTestFixture fixture = RunStateMachineImplTestFixture.createRunning(calculatedRunMock);

        // Then
        assertThat(fixture.runStateMachine().isActive(fixture.mainTestThread()), is(true));
    }

    @Test
    public void testFromRunningToRecording() {
        // Given
        CalculatedRun calculatedRunMock = mock(CalculatedRun.class);
        when(calculatedRunMock.isActive(anyInt())).thenReturn(false);
        RunStateMachineImplTestFixture fixture = RunStateMachineImplTestFixture.createRunning(calculatedRunMock);

        // When
        fixture.runStateMachine().setStateRecording();

        // Then
        assertThat(fixture.runStateMachine().isActive(fixture.mainTestThread()), is(true));
    }


}
