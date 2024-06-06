package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SendEventContext;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.parallelize.action.ParallelizeActionForRuntimeEvent;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class RunStateMachineImplAfterRuntimeEventTest {
    @Test
    public void testRunning() {
        CalculatedRun calculatedRunMock = mock(CalculatedRun.class);
        runTest(RunStateMachineImplTestFixture.createRunning(calculatedRunMock));
    }

    @Test
    public void testInitial() {
        runTest(RunStateMachineImplTestFixture.createInitial());
    }

    private void runTest(RunStateMachineImplTestFixture testFixture) {
        // Given
        RuntimeEvent firstEvent = mock(RuntimeEvent.class);
        ParallelizeActionForRuntimeEvent firstAction = new ParallelizeActionForRuntimeEvent(firstEvent);

        RuntimeEvent secondEvent = mock(RuntimeEvent.class);
        ParallelizeActionForRuntimeEvent secondAction = new ParallelizeActionForRuntimeEvent(secondEvent);

        // When
        testFixture.runStateMachine().after(firstAction, testFixture.mainTestThread());
        testFixture.runStateMachine().after(secondAction, testFixture.mainTestThread());

        // Then
        verify(firstEvent).send((SendEventContext) any());
        verify(secondEvent).send((SendEventContext) any());
    }
}
