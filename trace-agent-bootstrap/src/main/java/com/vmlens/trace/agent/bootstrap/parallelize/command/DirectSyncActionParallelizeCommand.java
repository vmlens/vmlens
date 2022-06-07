package com.vmlens.trace.agent.bootstrap.parallelize.command;

import com.vmlens.trace.agent.bootstrap.interleave.blockFactory.SyncAction;

import static com.vmlens.trace.agent.bootstrap.parallelize.command.ExecuteResult.Running;

public class DirectSyncActionParallelizeCommand implements ParallelizeCommand {

    private final SyncAction syncAction;
    private final long threadId;

    public DirectSyncActionParallelizeCommand(SyncAction syncAction, long threadId) {
        this.syncAction = syncAction;
        this.threadId = threadId;
    }

    @Override
    public ExecuteResult execute(RunStateContext runStateContext) {
        runStateContext.afterSyncAction(syncAction, threadId);
        return Running;
    }
}
