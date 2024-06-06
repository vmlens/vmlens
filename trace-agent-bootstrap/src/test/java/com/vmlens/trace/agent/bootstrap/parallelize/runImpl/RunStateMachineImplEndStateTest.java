package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RunStateMachineImplEndStateTest {
    @Test
    public void testFromRunningToEnd() {
        // Given
        CalculatedRun calculatedRunMock = mock(CalculatedRun.class);
        when(calculatedRunMock.isActive(anyInt())).thenReturn(false);
        RunStateMachineImplTestFixture fixture = RunStateMachineImplTestFixture.createRunning(calculatedRunMock);

        ThreadLocalForParallelize mainThread = new ThreadLocalForParallelize(2L, fixture.queueIn());
        mainThread.setThreadLocalDataWhenInTest(fixture.mainTestThread());

        // When
        fixture.runStateMachine().end(mainThread);

        // Then
        assertThat(fixture.runStateMachine().isActive(fixture.mainTestThread()), is(true));
        assertThat(mainThread.getThreadLocalDataWhenInTest(), nullValue());
    }
}
