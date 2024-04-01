package com.vmlens.trace.agent.bootstrap.testFixture;

import com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRunObserverNoOp;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.RunStateMachineFactoryImpl;

import java.util.concurrent.locks.ReentrantLock;

import static org.mockito.Mockito.mock;

public class InitialRunMockFactory {

    public Run create(int loopId, int runId) {
        InterleaveLoop interleaveLoopMock = mock(InterleaveLoop.class);
        WaitNotifyStrategy waitNotifyStrategyMock = mock(WaitNotifyStrategy.class);
        RunStateMachine runStateMachine = new RunStateMachineFactoryImpl()
                .createInitial(loopId, runId, new ActualRun(new ActualRunObserverNoOp()));

        return new Run(new ReentrantLock(), 0, waitNotifyStrategyMock, runStateMachine);
    }

}
