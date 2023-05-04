package com.vmlens.trace.agent.bootstrap.parallelize.facade;


import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.InterleaveRunJsonMemento;
import com.vmlens.trace.agent.bootstrap.interleave.loop.AgentLoggerForTest;
import com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.*;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.RunStateMachineImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.ThreadLocalWrapperMock;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestFromParallelizeFacadeToActualRun {
    @Test
    public void volatileReadAndWrite() {
        // Mocks
        WaitNotifyStrategy waitNotifyStrategyMock = mock(WaitNotifyStrategy.class);
        CalculatedRun calculatedRun = mock(CalculatedRun.class);
        when(calculatedRun.isActive(anyInt())).thenReturn(true);

        // Given
        ActualRun actualRun = new ActualRun(calculatedRun);
        ThreadLocalWrapper mainThread = new ThreadLocalWrapperMock(1L);
        TestThreadState threadStateMain = new TestThreadState(mainThread);
        RunStateMachineImpl runStateMachineImpl = new RunStateMachineImpl(actualRun, threadStateMain);
        Run run = new Run(0,waitNotifyStrategyMock,runStateMachineImpl,threadStateMain);

        ThreadLocalWrapper secondThread = new ThreadLocalWrapperMock(20L);
        TestThreadState threadStateSecond = new TestThreadState(secondThread);
        threadStateSecond.createNewParallelizedThreadLocal(run, 1);
        // When
        ParallelizeFacade.afterFieldAccessVolatile(mainThread,1, MemoryAccessType.IS_READ);
        ParallelizeFacade.afterFieldAccessVolatile(secondThread,1, MemoryAccessType.IS_WRITE);

        // Then
        InterleaveLoop.debug(new AgentLoggerForTest(), actualRun.actualRun());
        System.out.println(InterleaveRunJsonMemento.toJson(actualRun.actualRun()));
        assertThat(actualRun.actualRun(),is(InterleaveRunJsonMemento.load("volatileReadAndWrite")));
    }
}
