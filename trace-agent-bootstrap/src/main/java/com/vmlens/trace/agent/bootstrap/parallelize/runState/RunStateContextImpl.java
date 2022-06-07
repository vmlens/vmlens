package com.vmlens.trace.agent.bootstrap.parallelize.runState;

import com.vmlens.trace.agent.bootstrap.interleave.blockFactory.SyncAction;
import com.vmlens.trace.agent.bootstrap.interleave.facade.InterleaveFacade;
import com.vmlens.trace.agent.bootstrap.parallelize.command.RunStateContext;

public class RunStateContextImpl implements RunStateContext {
    public final InterleaveFacade interleaveFacade;
    public final ThreadIdToState threadIdToState;

    public RunStateContextImpl(InterleaveFacade interleaveFacade, ThreadIdToState threadIdToState) {
        this.interleaveFacade = interleaveFacade;
        this.threadIdToState = threadIdToState;
    }

    @Override
    public boolean needsToWait(long threadId) {
        return interleaveFacade.needsToWait(threadIdToState.threadIndex(threadId), threadIdToState);
    }

    @Override
    public void afterSyncAction(SyncAction syncAction, long threadId) {
        threadIdToState.afterSyncAction(interleaveFacade, syncAction, threadId);
    }
}
