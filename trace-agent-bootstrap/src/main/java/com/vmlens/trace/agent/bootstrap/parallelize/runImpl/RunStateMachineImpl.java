package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;

public class RunStateMachineImpl implements RunStateMachine {
    private RunState runState;
    public RunStateMachineImpl(CalculatedRun calculatedRun) {
        this.runState = new RunStateRunning(calculatedRun);
    }
    @Override
    public boolean isActive(long threadId) {
        return runState.isActive(threadId);
    }
    @Override
    public void after(ParallelizeAction action) {
        runState = runState.prepare(action);
        runState.after(action);
    }
    @Override
    public boolean newThread(Thread newThread) {
        RunState temp = runState.newThread(newThread);
        if(temp  != null){
            runState = temp;
            return true;
        }
        return false;
    }
    @Override
    public void end() {
        runState = new RunStateEnd();
    }
}
