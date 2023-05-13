package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadStartFactory;
import com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.actionImpl.ThreadStart;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class TestRunThreadStart {

    @Test
    public void testThreadStart() {
        // Basic Mocks
        WaitNotifyStrategy waitNotifyStrategyMock = mock(WaitNotifyStrategy.class);
        CalculatedRun calculatedRunMock = mock(CalculatedRun.class);
        InterleaveLoop interleaveLoopMock = mock(InterleaveLoop.class);

        // Main Thread Mocks
        TestThreadState threadLocalMainThread = new TestThreadState(new ThreadLocalWrapperMock(1L));
        // Second Thread Mocks
        RunnableOrThreadWrapper secondThreadWrapper = new RunnableOrThreadWrapper(new Object());
        TestThreadState localStateSecondThread = new TestThreadState(new ThreadLocalWrapperMock(20L));

        // If we create a new Run the containing thread gets a new thread local
        // ToDo
        RunStateMachineImpl runStateMachineImpl = null; // new RunStateMachineImpl(calculatedRunMock, threadLocalMainThread,interleaveLoopMock);
        Run run = new Run(0, waitNotifyStrategyMock, runStateMachineImpl, threadLocalMainThread);
        // then createNewParallelizedThreadLocal gets called and thread is 0
        assertThat(threadLocalMainThread.threadIndex(), is(0));
        // and is added to threadIdToStatethreadLocalMainThread
        assertThat(runStateMachineImpl.threadIdToState.threadIndexForThreadId(1L), is(0));

        // When we start a new runnable
        ThreadStart threadStart = new ThreadStart(secondThreadWrapper);
        runStateMachineImpl.after(threadStart, threadLocalMainThread);
        // then the thread is inactive
        assertThat(runStateMachineImpl.isActive(threadLocalMainThread), is(false));
        // which was calculated through the state not calculated run
        verify(calculatedRunMock, times(0)).isActive(anyInt());

        // and begin the second thread
        run.newTask(secondThreadWrapper, localStateSecondThread);

        // and is added to threadIdToState with index 1
        assertThat(runStateMachineImpl.threadIdToState.threadIndexForThreadId(20L), is(1));
        // and ThreadStartFactory is added to calculated run when runStateMachineImpl end is called
        TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> expectedList = new TLinkedList<>();
        expectedList.add(new TLinkableWrapper(new ThreadStartFactory(0, 1)));
        runStateMachineImpl.end();
        // ToDo implement check
        //verify(interleaveLoopMock).addActualRun(expectedList);
    }
}
