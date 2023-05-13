package com.vmlens.trace.agent.bootstrap.parallelize.facade;


import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ActualRunJsonMemento;
import com.vmlens.trace.agent.bootstrap.interleave.loop.AgentLoggerForTest;
import com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRunObserverNoOp;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.RunStateMachineImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.RunStateRecording;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.ThreadIdToState;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.ThreadLocalWrapperMock;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class TestFromParallelizeFacadeToActualRun {
    @Test
    public void volatileReadAndWrite() {
        // Mocks
        WaitNotifyStrategy waitNotifyStrategyMock = mock(WaitNotifyStrategy.class);
        InterleaveLoop interleaveLoopMock = mock(InterleaveLoop.class);
        // Given
        ThreadLocalWrapper mainThread = new ThreadLocalWrapperMock(1L);
        TestThreadState threadStateMain = new TestThreadState(mainThread);
        ActualRun actualRun = new ActualRun(new ActualRunObserverNoOp());
        ThreadIdToState threadIdToState = new ThreadIdToState();
        RunStateMachineImpl runStateMachineImpl = new RunStateMachineImpl(actualRun, threadStateMain,
                interleaveLoopMock, threadIdToState, new RunStateRecording(actualRun, threadIdToState));
        Run run = new Run(0, waitNotifyStrategyMock, runStateMachineImpl, threadStateMain);

        ThreadLocalWrapper secondThread = new ThreadLocalWrapperMock(20L);
        TestThreadState threadStateSecond = new TestThreadState(secondThread);
        threadStateSecond.createNewParallelizedThreadLocal(run, 1);
        // When
        ParallelizeFacade.afterFieldAccessVolatile(mainThread, 1, MemoryAccessType.IS_READ);
        ParallelizeFacade.afterFieldAccessVolatile(secondThread, 1, MemoryAccessType.IS_WRITE);

        // Then
        actualRun.debug(new AgentLoggerForTest());
        assertThat(actualRun.actualRun(), is(ActualRunJsonMemento.load("volatileReadAndWrite")));
    }
}
