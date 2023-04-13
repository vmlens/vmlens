package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;

/**
 * null object design pattern
 */
public class RunStateEnd implements RunState {
    @Override
    public boolean isActive(long threadId) {
        return true;
    }
    @Override
    public RunState newThread(Thread newThread) {
        return this;
    }
    @Override
    public void after(ParallelizeAction action) {

    }
    @Override
    public RunState prepare(ParallelizeAction action) {
        return this;
    }
}
