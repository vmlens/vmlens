package com.vmlens.trace.agent.bootstrap.parallelize.run;

/**
 * Either a factory for an interleave action or an event or both.
 */
public interface ParallelizeAction {
    RunState nextState(ActionContext context, ThreadLocalDataWhenInTest threadLocalDataWhenInTest);

    void addInterleaveActionAndOrEvent(ActionContext context, ThreadLocalDataWhenInTest threadLocalDataWhenInTest);
}
