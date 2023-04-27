package com.vmlens.trace.agent.bootstrap.parallelize.run;

public interface ParallelizeAction {
    RunState nextState(ActionContext context, TestThreadState testThreadState);
    void addInterleaveAction(ActionContext context, TestThreadState testThreadState);
}
