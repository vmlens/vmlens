package com.vmlens.trace.agent.bootstrap.interleave.activelock;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.LockEnterImpl;
import com.vmlens.trace.agent.bootstrap.interleave.lock.MonitorKey;
import com.vmlens.trace.agent.bootstrap.interleave.lock.Lock;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.Position.p;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ActiveLockCollectionTest {

    @Test
    public void testActiveOneThreadIndex() {
        // Given
        ActiveLockCollection mapContainingStack = new ActiveLockCollection();
        Lock firstMonitor = new Lock(new MonitorKey(0));
        Lock secondMonitor = new Lock(new MonitorKey(19));
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
        Lock firstMonitor = new Lock(new MonitorKey(0));
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
