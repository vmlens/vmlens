package com.vmlens.trace.agent.bootstrap.parallelize.testFixture;

public class ThreadModel {

    public final SyncActionModel[] syncActionModelArray;

    public ThreadModel(SyncActionModel[] syncActionModelArray) {
        this.syncActionModelArray = syncActionModelArray;
    }

    public static ThreadModel t(SyncActionModel... syncActionModels) {
        return new ThreadModel(syncActionModels);
    }

}
