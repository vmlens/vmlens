package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWhenInTestForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstates.ActiveStrategyRecording;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstates.RunStateActive;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class RunStateMachineTestWrapper {

    private final RunStateMachine runStateMachine;
    private final ThreadLocalDataWhenInTestMap runContext;
    private final ActualRun actualRun;
    private final ThreadLocalWhenInTestForParallelize threadLocalWhenInTest;
    private final Run run;
    private final ThreadLocalWhenInTestForParallelize eventThread;
    private final ThreadLocalWhenInTestForParallelize otherThread;
    private final RunnableOrThreadWrapper startedThread =
            new RunnableOrThreadWrapper(new Object());

    public RunStateMachineTestWrapper(ActiveStrategyRecording activeStrategyRecording) {
        runContext = new ThreadLocalDataWhenInTestMap();
        actualRun = new ActualRunMock(new ActualRunMockStrategyTake());
        runStateMachine = new RunStateMachineImpl(actualRun, runContext,
                new RunStateActive(activeStrategyRecording));
        threadLocalWhenInTest = mock(ThreadLocalWhenInTestForParallelize.class);
        run = mock(Run.class);
        eventThread = runContext.createForMainTestThread(run, 1L);
        otherThread = runContext.createForMainTestThread(run, 5L);
    }

    public static RunStateMachineTestWrapper createRecording() {
        return new RunStateMachineTestWrapper(new ActiveStrategyRecording());
    }

    public void assertBehavesAsRunStateEnd() {
        assertThat(runStateMachine.isActive(eventThread), is(true));
        assertThat(runStateMachine.isActive(otherThread), is(true));
        assertThat(runStateMachine.after(mock(RuntimeEvent.class), eventThread), is(nullValue()));
        assertThat(runStateMachine.processNewTestTask(startedThread, new ThreadLocalForParallelize(12L), run),
                is(nullValue()));
        assertThat(runStateMachine.endAtomicOperation(mock(RuntimeEvent.class), eventThread), is(nullValue()));
    }

    public void assertBehavesAsRunStateActiveActiveStrategyRecording() {
        RuntimeEvent runtimeEvent = mock(RuntimeEvent.class);
        assertThat(runStateMachine.isActive(eventThread), is(true));
        assertThat(runStateMachine.isActive(otherThread), is(true));
        assertThat(runStateMachine.after(runtimeEvent, eventThread), is(runtimeEvent));
        assertThat(runStateMachine.processNewTestTask(startedThread, new ThreadLocalForParallelize(12L), run),
                is(nullValue()));
        assertThat(runStateMachine.isStartAtomicOperationPossible(), is(true));
    }

    public void assertBehavesAsRunStateAtomicOperation() {
        RuntimeEvent runtimeEvent = mock(RuntimeEvent.class);
        assertThat(runStateMachine.isActive(eventThread), is(true));
        assertThat(runStateMachine.isActive(otherThread), is(false));
        assertThat(runStateMachine.after(runtimeEvent, eventThread), is(runtimeEvent));
        assertThat(runStateMachine.processNewTestTask(startedThread, new ThreadLocalForParallelize(12L), run),
                is(nullValue()));
        assertThat(runStateMachine.isStartAtomicOperationPossible(), is(false));
    }

    public void assertBehavesAsRunStateNewThreadStarted() {
        assertThat(runStateMachine.isActive(eventThread), is(false));
        assertThat(runStateMachine.isActive(otherThread), is(false));
        assertThat(runStateMachine.after(mock(RuntimeEvent.class), eventThread), is(notNullValue()));
        assertThat(runStateMachine.isStartAtomicOperationPossible(), is(false));
    }

    public void assertBehavesAsRunStateAtomicOperationWithNewThreadStarted() {
        RuntimeEvent runtimeEvent = mock(RuntimeEvent.class);
        assertThat(runStateMachine.isActive(eventThread), is(true));
        assertThat(runStateMachine.isActive(otherThread), is(false));
        assertThat(runStateMachine.after(runtimeEvent, eventThread), is(runtimeEvent));
        assertThat(runStateMachine.isStartAtomicOperationPossible(), is(false));
    }

    public void assertNewThreadIndex(int threadIndex) {
        assertThat(threadIndex, is(not(eventThread.threadIndex())));
        assertThat(threadIndex, is(not(otherThread.threadIndex())));
    }

    ThreadLocalWhenInTest eventProcessNewTestTask(ThreadLocalForParallelize threadLocalForParallelize) {
        return runStateMachine.processNewTestTask(startedThread, threadLocalForParallelize, run);
    }

    void eventStartAtomicOperation() {
        runStateMachine.startAtomicOperation(eventThread);
    }

    void eventStartAtomicOperationWithNewThread() {
        runStateMachine.startAtomicOperationWithNewThread(eventThread, startedThread);
    }

    void eventEndAtomicOperation(RuntimeEvent runtimeEvent) {
        runStateMachine.endAtomicOperation(runtimeEvent, eventThread);
    }

    void eventEnd(ThreadLocalForParallelize threadLocalForParallelize) {
        runStateMachine.end(threadLocalForParallelize);
    }


}
