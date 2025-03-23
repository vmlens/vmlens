package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RunStateMachineImplCanTest {

    @Test
    public void stateEnd() {
        // Given
        RunStateMachineTestWrapper runStateMachineTestWrapper = RunStateMachineTestWrapper.createRecording();
        RunStateMachine runStateMachine = runStateMachineTestWrapper.runStateMachine();
        runStateMachineTestWrapper.eventEnd(new ThreadLocalForParallelize(new ThreadForParallelizeMock(6L, "threadName")));

        // Then can* always returns true
        assertThat(runStateMachine.canProcessEndOfOperation(runStateMachineTestWrapper.eventThread()), is(true));
        assertThat(runStateMachine.canProcessEndOfOperation(runStateMachineTestWrapper.otherThread()), is(true));
        assertThat(runStateMachine.canStartAtomicOperation(), is(true));

        // even after start of an atomic operation
        runStateMachineTestWrapper.eventStartAtomicOperation();
        assertThat(runStateMachine.canProcessEndOfOperation(runStateMachineTestWrapper.eventThread()), is(true));
        assertThat(runStateMachine.canProcessEndOfOperation(runStateMachineTestWrapper.otherThread()), is(true));
        assertThat(runStateMachine.canStartAtomicOperation(), is(true));
    }

    @Test
    public void stateRecording() {
        // Given
        RunStateMachineTestWrapper runStateMachineTestWrapper = RunStateMachineTestWrapper.createRecording();
        RunStateMachine runStateMachine = runStateMachineTestWrapper.runStateMachine();

        // similar to end
        assertThat(runStateMachine.canProcessEndOfOperation(runStateMachineTestWrapper.eventThread()), is(true));
        assertThat(runStateMachine.canProcessEndOfOperation(runStateMachineTestWrapper.otherThread()), is(true));
        assertThat(runStateMachine.canStartAtomicOperation(), is(true));

        // during atomic operation canStartAtomicOperation and canProcessEndOfOperation returns false
        runStateMachineTestWrapper.eventStartAtomicOperation();
        assertThat(runStateMachine.canProcessEndOfOperation(runStateMachineTestWrapper.eventThread()), is(true));
        assertThat(runStateMachine.canProcessEndOfOperation(runStateMachineTestWrapper.otherThread()), is(false));
        assertThat(runStateMachine.canStartAtomicOperation(), is(false));
    }

}
