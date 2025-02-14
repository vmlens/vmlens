package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.PluginEventOnly;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWhenInTestForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstates.ActiveStrategyRecording;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstates.RunStateActive;
import gnu.trove.list.linked.TLinkedList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        eventThread = runContext.createForMainTestThread(run, mock(ThreadLocalForParallelize.class), new TLinkedList<>());
        otherThread = runContext.createForMainTestThread(run, mock(ThreadLocalForParallelize.class), new TLinkedList<>());
    }

    public static RunStateMachineTestWrapper createRecording() {
        return new RunStateMachineTestWrapper(new ActiveStrategyRecording());
    }

    public void assertBehavesAsRunStateEnd() {
        assertThat(runStateMachine.canProcessEndOfOperation(eventThread), is(true));
        assertThat(runStateMachine.canProcessEndOfOperation(otherThread), is(true));
        assertThat(runStateMachine.processNewTestTask(startedThread, new ThreadLocalForParallelize(12L, "threadName"), run)
                        .threadLocalWhenInTest(),
                is(nullValue()));
        assertThat(runStateMachine.endAtomicOperation(mock(RuntimeEvent.class), eventThread), is(nullValue()));
        assertRuntimeEventGetsNotProcessed();
    }

    public void assertBehavesAsRunStateActiveActiveStrategyRecording() {
        assertThat(runStateMachine.canProcessEndOfOperation(eventThread), is(true));
        assertThat(runStateMachine.canProcessEndOfOperation(otherThread), is(true));
        assertThat(runStateMachine.processNewTestTask(startedThread, new ThreadLocalForParallelize(12L, "threadName"), run)
                        .threadLocalWhenInTest(),
                is(nullValue()));
        assertThat(runStateMachine.canStartAtomicOperation(), is(true));
        assertRuntimeEventGetsProcessed();
    }

    public void assertBehavesAsRunStateAtomicOperation() {
        assertThat(runStateMachine.canProcessEndOfOperation(eventThread), is(true));
        assertThat(runStateMachine.canProcessEndOfOperation(otherThread), is(false));
        assertThat(runStateMachine.processNewTestTask(startedThread, new ThreadLocalForParallelize(12L, "threadName"), run)
                        .threadLocalWhenInTest(),
                is(nullValue()));
        assertThat(runStateMachine.canStartAtomicOperation(), is(false));
        assertRuntimeEventGetsProcessed();
    }

    public void assertBehavesAsRunStateNewThreadStarted() {
        assertThat(runStateMachine.canProcessEndOfOperation(eventThread), is(false));
        assertThat(runStateMachine.canProcessEndOfOperation(otherThread), is(false));
        assertThat(runStateMachine.canStartAtomicOperation(), is(false));
        assertRuntimeEventGetsProcessed();
    }

    public void assertBehavesAsRunStateAtomicOperationWithNewThreadStarted() {
        assertThat(runStateMachine.canProcessEndOfOperation(eventThread), is(true));
        assertThat(runStateMachine.canProcessEndOfOperation(otherThread), is(false));
        assertThat(runStateMachine.canStartAtomicOperation(), is(false));
        assertRuntimeEventGetsProcessed();
    }

    public void assertNewThreadIndex(int threadIndex) {
        assertThat(threadIndex, is(not(eventThread.threadIndex())));
        assertThat(threadIndex, is(not(otherThread.threadIndex())));
    }

    ThreadLocalWhenInTest eventProcessNewTestTask(ThreadLocalForParallelize threadLocalForParallelize) {
        return runStateMachine.processNewTestTask(startedThread, threadLocalForParallelize, run)
                .threadLocalWhenInTest();
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

    public RunStateMachine runStateMachine() {
        return runStateMachine;
    }

    public ThreadLocalWhenInTestForParallelize eventThread() {
        return eventThread;
    }

    public ThreadLocalWhenInTestForParallelize otherThread() {
        return otherThread;
    }

    private void assertRuntimeEventGetsProcessed() {
        RuntimeEvent runtimeEvent = mock(PluginEventOnly.class);
        runStateMachine.after(runtimeEvent, eventThread);
        verify(runtimeEvent).after(any());
    }

    private void assertRuntimeEventGetsNotProcessed() {
        RuntimeEvent runtimeEvent = mock(PluginEventOnly.class);
        runStateMachine.after(runtimeEvent, eventThread);
        verify(runtimeEvent, never()).after(any());
    }


}
