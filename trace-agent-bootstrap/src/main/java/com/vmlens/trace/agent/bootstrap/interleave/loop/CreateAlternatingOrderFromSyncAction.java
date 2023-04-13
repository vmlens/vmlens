package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.Logger;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainerOld;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionOld.CreateBlockListCollectionFromSyncActions;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionOld.SyncActionAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock.DeadlockContext;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static java.lang.Math.max;

public class CreateAlternatingOrderFromSyncAction {

    private final Logger logger;
    private final CreateBlockListCollectionFromSyncActions createBlockListCollectionFromSyncActions;

    public CreateAlternatingOrderFromSyncAction(Logger logger) {
        this.logger = logger;
        this.createBlockListCollectionFromSyncActions = new CreateBlockListCollectionFromSyncActions(logger);
    }

    public AlternatingOrderContainerOld create(TLinkedList<TLinkableWrapper<SyncActionAndPosition>> actualRun) {
        DeadlockContext deadlockContext = new DeadlockContext();
        return new AlternatingOrderContainerOld(logger,
                createBlockListCollectionFromSyncActions.create(actualRun).createOrder(deadlockContext),
                calculateLength(actualRun));
    }

    private int[] calculateLength(TLinkedList<TLinkableWrapper<SyncActionAndPosition>> actualRun) {
        int maxThreadIndex = 0;
        for (TLinkableWrapper<SyncActionAndPosition> linkable : actualRun) {
            maxThreadIndex = max(linkable.element.position.threadIndex, maxThreadIndex);
        }
        int[] length = new int[maxThreadIndex + 1];
        for (TLinkableWrapper<SyncActionAndPosition> linkable : actualRun) {
            int i = linkable.element.position.threadIndex;
            length[i] = max(linkable.element.position.positionInThread + 1, length[i]);
        }
        return length;
    }

}
