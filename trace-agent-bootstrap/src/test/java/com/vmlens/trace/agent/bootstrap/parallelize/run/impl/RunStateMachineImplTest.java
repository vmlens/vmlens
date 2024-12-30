package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEventVisitor;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class RunStateMachineImplTest {


    @Test
    public void threadBeginAfterRunStateAtomicOperationWithNewThreadStarted() {
        // Given
        ThreadLocalForParallelize threadLocalForParallelize = new ThreadLocalForParallelize(5L);
        ThreadStartEvent runtimeEvent = createInterleaveActionFactoryMock(ThreadStartEvent.class);
        RunStateMachineTestWrapper runStateMachineTestWrapper = RunStateMachineTestWrapper.createRecording();

        // start atomic new thread start
        runStateMachineTestWrapper.eventStartAtomicOperationWithNewThread();
        runStateMachineTestWrapper.assertBehavesAsRunStateAtomicOperationWithNewThreadStarted();

        // new thread begin
        ThreadLocalWhenInTest newThreadLocalWhenInTest = runStateMachineTestWrapper.eventProcessNewTestTask(threadLocalForParallelize);
        assertThat(newThreadLocalWhenInTest, is(notNullValue()));
        assertThat(threadLocalForParallelize.getThreadLocalDataWhenInTest(), is(notNullValue()));
        runStateMachineTestWrapper.assertBehavesAsRunStateAtomicOperation();

        // after thread start
        runStateMachineTestWrapper.eventEndAtomicOperation(runtimeEvent);
        verify(runtimeEvent).create();
        verify(runtimeEvent).setStartedThreadIndex(anyInt());
        runStateMachineTestWrapper.assertBehavesAsRunStateActiveActiveStrategyRecording();
    }

    @Test
    public void threadBeginAfterRunStateNewThreadStarted() {
        // Given
        ThreadLocalForParallelize threadLocalForParallelize = new ThreadLocalForParallelize(5L);
        ThreadStartEvent runtimeEvent = createInterleaveActionFactoryMock(ThreadStartEvent.class);
        RunStateMachineTestWrapper runStateMachineTestWrapper = RunStateMachineTestWrapper.createRecording();

        // start atomic new thread start
        runStateMachineTestWrapper.eventStartAtomicOperationWithNewThread();

        // after thread start
        runStateMachineTestWrapper.eventEndAtomicOperation(runtimeEvent);
        verify(runtimeEvent).create();
        verify(runtimeEvent).setStartedThreadIndex(anyInt());
        runStateMachineTestWrapper.assertBehavesAsRunStateNewThreadStarted();


        // new thread begin
        ThreadLocalWhenInTest newThreadLocalWhenInTest = runStateMachineTestWrapper.eventProcessNewTestTask(threadLocalForParallelize);
        assertThat(newThreadLocalWhenInTest, is(notNullValue()));
        assertThat(threadLocalForParallelize.getThreadLocalDataWhenInTest(), is(notNullValue()));
        runStateMachineTestWrapper.assertBehavesAsRunStateActiveActiveStrategyRecording();
    }

    @Test
    public void recordingToEnd() {
        // Given
        ThreadLocalForParallelize threadLocalForParallelize = new ThreadLocalForParallelize(5L);
        RunStateMachineTestWrapper runStateMachineTestWrapper = RunStateMachineTestWrapper.createRecording();

        // end event
        runStateMachineTestWrapper.eventEnd(threadLocalForParallelize);
        runStateMachineTestWrapper.assertBehavesAsRunStateEnd();
        assertThat(threadLocalForParallelize.getThreadLocalDataWhenInTest(), is(nullValue()));
    }

    @Test
    public void atomicOperation() {
        // Given
        InterleaveActionFactory runtimeEvent = createInterleaveActionFactoryMock(InterleaveActionFactory.class);
        RunStateMachineTestWrapper runStateMachineTestWrapper = RunStateMachineTestWrapper.createRecording();

        // start atomic
        runStateMachineTestWrapper.eventStartAtomicOperation();
        runStateMachineTestWrapper.assertBehavesAsRunStateAtomicOperation();

        // after atomic operation
        runStateMachineTestWrapper.eventEndAtomicOperation(runtimeEvent);
        verify(runtimeEvent).create();
        runStateMachineTestWrapper.assertBehavesAsRunStateActiveActiveStrategyRecording();
    }

    private <EVENT extends InterleaveActionFactory> EVENT createInterleaveActionFactoryMock(Class<EVENT> mockClass) {
        EVENT runtimeEvent = mock(mockClass);
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                ((RuntimeEventVisitor) invocationOnMock.getArguments()[0])
                        .visit((InterleaveActionFactory) invocationOnMock.getMock());
                return null;
            }
        }).when(runtimeEvent).accept(any());
        return runtimeEvent;
    }

}
