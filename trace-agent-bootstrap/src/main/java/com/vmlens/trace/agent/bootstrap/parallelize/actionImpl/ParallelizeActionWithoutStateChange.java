package com.vmlens.trace.agent.bootstrap.parallelize.actionImpl;

import com.vmlens.trace.agent.bootstrap.parallelize.run.ActionContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;

public abstract class ParallelizeActionWithoutStateChange implements ParallelizeAction {
    @Override
    public RunState nextState(ActionContext context, ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        return context.current();
    }

}
