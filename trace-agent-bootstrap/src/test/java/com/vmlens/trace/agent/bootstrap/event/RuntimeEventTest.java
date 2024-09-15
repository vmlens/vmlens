package com.vmlens.trace.agent.bootstrap.event;

import com.vmlens.trace.agent.bootstrap.callback.RunMock;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ActualRunMock;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ActualRunMockStrategyTake;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateActive;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Tests the values which are the same for all RuntimeEvent
 * e.g. does not depend on a visitor
 */

public class RuntimeEventTest {

    @Test
    public void runIdAndLoopId() {
        // Given
        int LOOP_ID = 5;
        int RUN_ID = 99;

        RuntimeEventGuineaPig runtimeEvent = new RuntimeEventGuineaPig();
        ReentrantLock reentrantLock = new ReentrantLock();
        WaitNotifyStrategy waitNotifyStrategy = mock(WaitNotifyStrategy.class);
        RunImpl run = new RunImpl(reentrantLock, waitNotifyStrategy, new RunStateMachineMock(), LOOP_ID, RUN_ID);

        // When
        RuntimeEvent result = run.after(runtimeEvent, null);

        // Then
        assertThat(result, CoreMatchers.<RuntimeEvent>is(runtimeEvent));
        assertThat(runtimeEvent.loopId(), is(LOOP_ID));
        assertThat(runtimeEvent.runId(), is(RUN_ID));
    }

    @Test
    public void runPosition() {
        // Given
        int RUN_POSITION = 7;

        RuntimeEventGuineaPig runtimeEvent = new RuntimeEventGuineaPig();
        ActualRunMockStrategyTake actualRunMockStrategyTake = new ActualRunMockStrategyTake(RUN_POSITION);
        RunStateActive runStateActive = new RunStateActive(new ActualRunMock(actualRunMockStrategyTake),
                null, null);

        // When
        RuntimeEvent result = runStateActive.processRuntimeEvent(runtimeEvent);

        // Then
        assertThat(result, CoreMatchers.<RuntimeEvent>is(runtimeEvent));
        assertThat(runtimeEvent.runPosition(), is(RUN_POSITION));
    }

    @Test
    public void threadIndex() {
        // Given
        int THREAD_INDEX = 99;
        RuntimeEventGuineaPig runtimeEvent = new RuntimeEventGuineaPig();
        ThreadLocalDataWhenInTest threadLocalDataWhenInTest = new ThreadLocalDataWhenInTest(new RunMock(), THREAD_INDEX);

        // When
        SerializableEvent result = threadLocalDataWhenInTest.after(runtimeEvent);

        // Then
        assertThat(result, CoreMatchers.<SerializableEvent>is(runtimeEvent));
        assertThat(runtimeEvent.threadIndex(), is(THREAD_INDEX));
    }
}
