package com.vmlens.trace.agent.bootstrap.interleave.domain;


public final class SyncActionAndPosition {

    public final Position position;
    public final int activeThreadCount;
    public final SyncAction syncAction;

    public SyncActionAndPosition(int activeThreadCount, Position position, SyncAction syncAction) {
        this.activeThreadCount = activeThreadCount;
        this.position = position;
        this.syncAction = syncAction;
    }

    // Fixme hir müssen single blöcke und intervalle gebaut werden
    public SingleSyncActionBlock createBlock() {
        return new SingleSyncActionBlock(this);
    }

    @Override
    public String toString() {
        return "SyncAction{" +
                "position=" + position +
                ", syncActionFromRun=" + syncAction +
                '}';
    }
}
