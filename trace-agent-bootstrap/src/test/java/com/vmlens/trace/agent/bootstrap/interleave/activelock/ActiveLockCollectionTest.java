package com.vmlens.trace.agent.bootstrap.interleave.activelock;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.LockEnterImpl;
import com.vmlens.trace.agent.bootstrap.interleave.lock.Monitor;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.Position.p;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ActiveLockCollectionTest {

    @Test
    public void testActiveOneThreadIndex() {
        // Given
        ActiveLockCollection mapContainingStack = new ActiveLockCollection();
        Monitor firstMonitor = new Monitor(0);
        Monitor secondMonitor = new Monitor(10);
        int threadIndex = 5;

        // When
        mapContainingStack.push(new ElementAndPosition<>(new LockEnterImpl(threadIndex, firstMonitor), p(threadIndex, 1)));
        mapContainingStack.push(new ElementAndPosition<>(new LockEnterImpl(threadIndex, secondMonitor), p(threadIndex, 5)));

        // Then
        assertThat(mapContainingStack.pop(threadIndex,firstMonitor.key()).position(), is(p(threadIndex, 1)));
        assertThat(mapContainingStack.pop(threadIndex,secondMonitor.key()).position(), is(p(threadIndex, 5)));
    }

    @Test
    public void testActiveOneMultipleIndex() {
        // Given
        ActiveLockCollection mapContainingStack = new ActiveLockCollection();
        Monitor firstMonitor = new Monitor(0);
        int threadIndex = 5;
        int secondIndex = 9;

        // When
        mapContainingStack.push(new ElementAndPosition<>(new LockEnterImpl(threadIndex, firstMonitor), p(threadIndex, 1)));
        mapContainingStack.push(new ElementAndPosition<>(new LockEnterImpl(secondIndex, firstMonitor), p(secondIndex, 5)));

        // Then
        assertThat(mapContainingStack.pop(secondIndex,firstMonitor.key()).position(), is(p(secondIndex, 5)));
        assertThat(mapContainingStack.pop(threadIndex,firstMonitor.key()).position(), is(p(threadIndex, 1)));
    }

}
