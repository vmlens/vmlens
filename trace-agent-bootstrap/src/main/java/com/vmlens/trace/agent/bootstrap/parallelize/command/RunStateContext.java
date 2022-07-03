package com.vmlens.trace.agent.bootstrap.parallelize.command;

import com.vmlens.trace.agent.bootstrap.interleave.syncAction.SyncAction;

public interface RunStateContext {

    boolean needsToWait(long threadId);

    void afterSyncAction(SyncAction syncAction, long threadId);
    // void startThread(Object started);
    //int beginThread(Object begun, long threadId);

}
