package com.vmlens.trace.agent.bootstrap.parallelize.actionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.block.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.InterleaveActionWithPositionFactoryImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;

public class InterleaveActionWithPositionFactoryFactory implements ParallelizeAction {

    private final InterleaveAction interleaveAction;
    private final long threadId;

    public InterleaveActionWithPositionFactoryFactory(InterleaveAction interleaveAction, long threadId) {
        this.interleaveAction = interleaveAction;
        this.threadId = threadId;
    }

    @Override
    public RunState prepare(RunContext context) {
        return context.current();
    }

    @Override
    public void addSyncAction(RunContext context) {
        context.getCalculatedRun().after(new InterleaveActionWithPositionFactoryImpl(
                interleaveAction,context.threadIndexForThreadId(threadId)));
    }


}
