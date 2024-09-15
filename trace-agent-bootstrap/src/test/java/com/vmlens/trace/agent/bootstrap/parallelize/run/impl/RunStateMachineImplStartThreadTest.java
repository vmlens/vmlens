package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.ThreadStartEvent;
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
        int EXPECTED_THREAD_INDEX = 7;

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
    }
}
