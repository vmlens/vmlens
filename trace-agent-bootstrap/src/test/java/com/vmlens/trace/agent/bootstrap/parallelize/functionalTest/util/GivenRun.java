package com.vmlens.trace.agent.bootstrap.parallelize.functionalTest.util;

import com.vmlens.trace.agent.bootstrap.parallelize.testFixture.SyncActionModel;

import java.util.HashMap;
import java.util.LinkedList;

import static com.vmlens.trace.agent.bootstrap.parallelize.functionalTest.util.CallbackStatePerThreadRepository.threadId;

public class GivenRun {

    public final int count;
    private final HashMap<Long, LinkedList<SyncActionModel>> threadIdToSyncActions =
            new HashMap<Long, LinkedList<SyncActionModel>>();

    public GivenRun(SyncActionModel[] array) {
        for (SyncActionModel syncActionModel : array) {
            LinkedList<SyncActionModel> syncActions = threadIdToSyncActions.get(threadId(syncActionModel.getThreadIndex()));
            if (syncActions == null) {
                syncActions = new LinkedList<SyncActionModel>();
                threadIdToSyncActions.put(threadId(syncActionModel.getThreadIndex()), syncActions);
            }
            syncActions.add(syncActionModel);
        }
        count = array.length;
    }

    public SyncActionModel pop(long threadId) {
        return threadIdToSyncActions.get(threadId).poll();
    }
}
