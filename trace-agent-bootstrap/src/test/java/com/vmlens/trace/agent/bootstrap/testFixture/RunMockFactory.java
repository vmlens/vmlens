package com.vmlens.trace.agent.bootstrap.testFixture;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThreadForParallelize;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.RunContext;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.RunStateMachineImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.RunStateRunning;

import java.util.concurrent.locks.ReentrantLock;

import static org.mockito.Mockito.mock;

public class RunMockFactory {

    public Run create(ActualRun actualRun, RunContext runContext) {
        InterleaveLoop interleaveLoopMock = mock(InterleaveLoop.class);
        WaitNotifyStrategy waitNotifyStrategyMock = mock(WaitNotifyStrategy.class);
        CalculatedRun calculatedRunMock = mock(CalculatedRun.class);
        TestThreadState threadLocalMainThread = new TestThreadState(new CallbackStatePerThreadForParallelize(1L, null));
        RunStateRunning runningState = new RunStateRunning(actualRun, calculatedRunMock, runContext);
        RunStateMachineImpl runStateMachineImpl = new RunStateMachineImpl(actualRun, threadLocalMainThread,
                interleaveLoopMock, runContext, runningState);
        return new Run(new ReentrantLock(), 0, waitNotifyStrategyMock, runStateMachineImpl, threadLocalMainThread);
    }

}
