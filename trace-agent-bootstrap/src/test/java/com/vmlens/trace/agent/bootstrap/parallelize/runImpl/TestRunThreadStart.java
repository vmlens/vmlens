package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadStartFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.actionImpl.ThreadStart;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class TestRunThreadStart {

    @Test
    public void testThreadStart() {
        // Basic Mocks
        WaitNotifyStrategy waitNotifyStrategyMock = mock(WaitNotifyStrategy.class);
        ActualRun calculatedRunMock = mock(ActualRun.class);

        // Main Thread Mocks
        TestThreadState threadLocalMainThread = new TestThreadState(new ThreadLocalWrapperMock(1L));
        // Second Thread Mocks
        RunnableOrThreadWrapper secondThreadWrapper = new RunnableOrThreadWrapper(new Object());
        TestThreadState localStateSecondThread = new TestThreadState(new ThreadLocalWrapperMock(20L));

        // If we create a new Run the containing thread gets a new thread local
        RunStateMachineImpl runStateMachineImpl = new RunStateMachineImpl(calculatedRunMock, threadLocalMainThread);
        Run run = new Run(0,waitNotifyStrategyMock,runStateMachineImpl,threadLocalMainThread);
        // then createNewParallelizedThreadLocal gets called and thread is 0
        assertThat(threadLocalMainThread.threadIndex(),is(0));
        // and is added to threadIdToStatethreadLocalMainThread
        assertThat(runStateMachineImpl.threadIdToState.threadIndexForThreadId(1L), is(0));

        // When we start a new runnable
        ThreadStart threadStart = new ThreadStart(secondThreadWrapper);
        runStateMachineImpl.after(threadStart,threadLocalMainThread);
        // then the thread is inactive
        assertThat(runStateMachineImpl.isActive(threadLocalMainThread),is(false));
        // which was calculated through the state not calculated run
        verify(calculatedRunMock,times(0)).isActive(anyInt());

        // and begin the second thread
        run.newTask(secondThreadWrapper,localStateSecondThread);

        // and is added to threadIdToState with index 1
        assertThat(runStateMachineImpl.threadIdToState.threadIndexForThreadId(20L), is(1));
        // and ThreadStartFactory was added to calculated run
        verify(calculatedRunMock).after(new ThreadStartFactory(0,1));
    }
}
