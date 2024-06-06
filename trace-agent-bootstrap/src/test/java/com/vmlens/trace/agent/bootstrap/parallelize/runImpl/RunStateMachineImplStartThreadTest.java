package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.action.ParallelizeActionForThreadStart;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.mock;

public class RunStateMachineImplStartThreadTest {

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
        ThreadLocalForParallelize startedThread = new ThreadLocalForParallelize(2L, testFixture.queueIn());
        RunnableOrThreadWrapper runnableOrThreadWrapper = new RunnableOrThreadWrapper(new Object());

        // When
        testFixture.runStateMachine().after(new ParallelizeActionForThreadStart(runnableOrThreadWrapper),
                testFixture.mainTestThread());

        // Then
        assertThat(testFixture.runStateMachine().isActive(testFixture.mainTestThread()), is(false));

        // When
        testFixture.runStateMachine().processNewTestTask(runnableOrThreadWrapper, startedThread, testFixture.run());

        // Then
        assertThat(startedThread.getThreadLocalDataWhenInTest(), is(notNullValue()));
        assertThat(testFixture.eventList().size(), is(1));
    }
}
