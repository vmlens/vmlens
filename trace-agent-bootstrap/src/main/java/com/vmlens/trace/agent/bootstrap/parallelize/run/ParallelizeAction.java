package com.vmlens.trace.agent.bootstrap.parallelize.run;

/**
 * Either a factory for an interleave action or an event or both.
 */
public interface ParallelizeAction {
    RunState nextState(ActionContext context, TestThreadState testThreadState);

    void addInterleaveActionAndOrEvent(ActionContext context, TestThreadState testThreadState);
}
