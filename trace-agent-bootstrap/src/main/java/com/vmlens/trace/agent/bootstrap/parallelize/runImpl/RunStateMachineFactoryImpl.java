package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachineFactory;

public class RunStateMachineFactoryImpl implements RunStateMachineFactory {

    @Override
    public RunStateMachine createRunning(int loopId, int runId, CalculatedRun calculatedRun, ActualRun actualRun) {
        RunContext runContext = new RunContext(loopId, runId);
        return new RunStateMachineImpl(actualRun, runContext,
                new RunStateRunning(actualRun, calculatedRun, runContext));
    }

    @Override
    public RunStateMachine createInitial(int loopId, int runId, ActualRun actualRun) {
        RunContext runContext = new RunContext(loopId, runId);
        return new RunStateMachineImpl(actualRun, runContext,
                new RunStateRecording(actualRun, runContext));
    }

}
