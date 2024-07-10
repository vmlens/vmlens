package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.VolatileFieldAccess;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class ActualRunTest {
    @Test
    public void volatileFieldAccess() {
        // Given
        CalculatedRun calculatedRun = mock(CalculatedRun.class);
        ActualRun actualRun = new ActualRun(new ActualRunObserverForCalculatedRun(calculatedRun));

        // When
        InterleaveInfo interleaveInfo = actualRun.after(new VolatileFieldAccess(1, 1, 1));

        // Then
        assertThat(actualRun.run().size(), is(1));
        assertThat(interleaveInfo.runPosition(), is(0));
        verify(calculatedRun, times(1)).incrementPositionInThread();

    }
}
