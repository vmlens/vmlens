package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadStart;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class RunStateMachineImplStartThreadTest {

    @Test
    public void testInitial() {
        // Given
        ThreadLocalForParallelize startedThread = new ThreadLocalForParallelize(2L);
        RunnableOrThreadWrapper runnableOrThreadWrapper = new RunnableOrThreadWrapper(new Object());
        RunStateMachineImplTestFixture testFixture = RunStateMachineImplTestFixture.createInitial();
        testFixture.runContext().getThreadIndexForNewTestThread();
        int expectedStartedThreadIndex = testFixture.runContext().getThreadIndexForNewTestThread() + 1;

        ThreadStartEvent startEvent = new ThreadStartEvent(runnableOrThreadWrapper);

        // When
        RuntimeEvent runtimeEvent = testFixture.runStateMachine().after(startEvent,
                testFixture.mainTestThread());

        assertThat(testFixture.runStateMachine().isActive(testFixture.mainTestThread()), is(false));

        // When
        testFixture.runStateMachine().processNewTestTask(runnableOrThreadWrapper, startedThread, testFixture.run());

        // Then
        assertThat(startedThread.getThreadLocalDataWhenInTest(), is(notNullValue()));
        assertThat(startedThread.getThreadLocalDataWhenInTest().threadIndex(), is(expectedStartedThreadIndex));

        // Both threads should be active, since initial is state recording
        assertThat(testFixture.runStateMachine().isActive(testFixture.mainTestThread()), is(true));
        assertThat(testFixture.runStateMachine().isActive(startedThread.getThreadLocalDataWhenInTest()), is(true));

        assertThat(testFixture.actualRunMock().interleaveActions().size(), is(1));
        ThreadStart threadStart = (ThreadStart) testFixture.actualRunMock().interleaveActions().get(0);
        assertThat(threadStart.threadIndex(), is(0));
        assertThat(threadStart.startedThreadIndex(), is(expectedStartedThreadIndex));

    }
}
