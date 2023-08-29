package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.LockOrMonitorEnter;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapContainingStack;
import com.vmlens.trace.agent.bootstrap.interleave.lockOrMonitor.Monitor;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.Position.p;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestMapContainingStack {

    @Test
    public void testStack() {
        // Given
        MapContainingStack mapContainingStack = new MapContainingStack();
        Monitor givenMonitor = new Monitor(0);

        // When
        mapContainingStack.push(new ElementAndPosition<LockOrMonitorEnter>(new LockOrMonitorEnterImpl(givenMonitor), p(0, 1)));
        mapContainingStack.push(new ElementAndPosition<LockOrMonitorEnter>(new LockOrMonitorEnterImpl(givenMonitor), p(0, 5)));

        // Then
        assertThat(mapContainingStack.pop(givenMonitor.key()).position(), is(p(0, 5)));
        assertThat(mapContainingStack.pop(givenMonitor.key()).position(), is(p(0, 1)));
    }

}
