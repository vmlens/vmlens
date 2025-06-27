package com.vmlens.trace.agent.bootstrap.interleave.activelock;


import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.lockkey.MonitorKey;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.Position.p;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ActiveLockCollectionTest {

    @Test
    public void testActiveOneThreadIndex() {
        // Given
        ActiveLockCollection mapContainingStack = new ActiveLockCollection();
        MonitorKey firstMonitor = new MonitorKey(0);
        MonitorKey secondMonitor = new MonitorKey(19);
        int threadIndex = 5;

        // When
        mapContainingStack.push(new LockEnterOperation( p(threadIndex, 1), firstMonitor));
        mapContainingStack.push(new LockEnterOperation( p(threadIndex, 5),  secondMonitor));

        // Then
        assertThat(mapContainingStack.pop(threadIndex,firstMonitor).position(), is(p(threadIndex, 1)));
        assertThat(mapContainingStack.pop(threadIndex,secondMonitor).position(), is(p(threadIndex, 5)));
    }

    @Test
    public void testActiveOneMultipleIndex() {
        // Given
        ActiveLockCollection mapContainingStack = new ActiveLockCollection();
        MonitorKey firstMonitor = new MonitorKey(0);
        int threadIndex = 5;
        int secondIndex = 9;

        // When
        mapContainingStack.push(new LockEnterOperation(p(threadIndex, 1),  firstMonitor));
        mapContainingStack.push(new LockEnterOperation( p(secondIndex, 5),  firstMonitor));

        // Then
        assertThat(mapContainingStack.pop(secondIndex,firstMonitor).position(), is(p(secondIndex, 5)));
        assertThat(mapContainingStack.pop(threadIndex,firstMonitor).position(), is(p(threadIndex, 1)));
    }

}
