package com.vmlens.trace.agent.bootstrap.parallelize.run;

/**
 * Either a factory for an interleave action or an event or both.
 */
public interface ParallelizeAction {
    RunStateAndRuntimeEvent execute(ActionContext context);

}
