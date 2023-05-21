package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadStartFactory;
import com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRunObserverNoOp;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.actionImpl.ThreadStart;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class TestRunStateMachine {

    private final WaitNotifyStrategy waitNotifyStrategyMock = mock(WaitNotifyStrategy.class);
    private final InterleaveLoop interleaveLoopMock = mock(InterleaveLoop.class);
    private CalculatedRun calculatedRunMock;
    private TestThreadState threadLocalMainThread;
    private RunnableOrThreadWrapper secondThreadWrapper;
    private TestThreadState localStateSecondThread;
    private ThreadIdToState threadIdToState;
    private ActualRun actualRun;

    @Before
    public void init() {
        // Basic Mocks
        calculatedRunMock = mock(CalculatedRun.class);

        // Main Thread Mocks
        threadLocalMainThread = new TestThreadState(new ThreadLocalWrapperMock(1L));

        // Second Thread Mocks
        secondThreadWrapper = new RunnableOrThreadWrapper(new Object());
        localStateSecondThread = new TestThreadState(new ThreadLocalWrapperMock(20L));

        threadIdToState = new ThreadIdToState();
        actualRun = new ActualRun(new ActualRunObserverNoOp());
    }

    @Test
    public void testStateRunningThreadStart() {
        RunStateRunning runningState = new RunStateRunning(actualRun, calculatedRunMock, threadIdToState);
        threadStart(runningState);
    }

    @Test
    public void testRunStateRecordingThreadStart() {
        RunStateRecording runningState = new RunStateRecording(actualRun, threadIdToState);
        threadStart(runningState);
    }

    private void threadStart(RunState initial) {
        // Expected
        TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> expectedList = new TLinkedList<>();
        expectedList.add(new TLinkableWrapper(new ThreadStartFactory(0, 1)));

        // Given
        RunStateMachineImpl runStateMachineImpl = new RunStateMachineImpl(actualRun, threadLocalMainThread,
                interleaveLoopMock, threadIdToState, initial);

        // When we create a new Run the containing thread gets a new thread local
        Run run = new Run(new Object(), 0, waitNotifyStrategyMock, runStateMachineImpl, threadLocalMainThread);

        // Then createNewParallelizedThreadLocal gets called and thread is 0
        assertThat(threadLocalMainThread.threadIndex(), is(0));

        // And is added to threadIdToStatethreadLocalMainThread
        assertThat(runStateMachineImpl.threadIdToState.threadIndexForThreadId(1L), is(0));

        // When we start a new runnable
        ThreadStart threadStart = new ThreadStart(secondThreadWrapper);
        runStateMachineImpl.after(threadStart, threadLocalMainThread);

        // Then the thread is inactive
        assertThat(runStateMachineImpl.isActive(threadLocalMainThread), is(false));
        // which was calculated through the state and not through calculated run
        verify(calculatedRunMock, times(0)).isActive(anyInt());

        // And begin the second thread
        run.newTask(secondThreadWrapper, localStateSecondThread);

        // and is added to threadIdToState with index 1
        assertThat(runStateMachineImpl.threadIdToState.threadIndexForThreadId(20L), is(1));

        // And ThreadStartFactory is added to calculated run when runStateMachineImpl end is called
        runStateMachineImpl.end();

        assertThat(actualRun.run(), is(expectedList));
    }


}
