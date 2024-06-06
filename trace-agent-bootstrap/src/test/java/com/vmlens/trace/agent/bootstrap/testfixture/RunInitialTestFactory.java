package com.vmlens.trace.agent.bootstrap.testfixture;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRunObserverNoOp;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateMachineFactoryImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadLocalDataWhenInTestMap;

import java.util.concurrent.locks.ReentrantLock;

import static org.mockito.Mockito.mock;

// Fixme refactor to test fixture
public class RunInitialTestFactory {

    public Run create(int loopId, int runId) {
        ThreadLocalDataWhenInTestMap runContext = new ThreadLocalDataWhenInTestMap(loopId, runId);
        WaitNotifyStrategy waitNotifyStrategyMock = mock(WaitNotifyStrategy.class);
        RunStateMachine runStateMachine = new RunStateMachineFactoryImpl()
                .createInitial(runContext, new ActualRun(new ActualRunObserverNoOp()));

        return new RunImpl(new ReentrantLock(), waitNotifyStrategyMock, runStateMachine);
    }
}
