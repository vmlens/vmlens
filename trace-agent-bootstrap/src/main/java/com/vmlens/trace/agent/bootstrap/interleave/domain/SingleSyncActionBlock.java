package com.vmlens.trace.agent.bootstrap.interleave.domain;

import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class SingleSyncActionBlock implements SyncActionBlock {

    private final SyncActionAndPosition syncActionAndPosition;

    public SingleSyncActionBlock(SyncActionAndPosition syncActionAndPosition) {
        this.syncActionAndPosition = syncActionAndPosition;
    }

    public void createOrder(SingleSyncActionBlock otherBlock,
                            TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrderElements,
                            TLinkedList<TLinkableWrapper<AlternatingOrderElement>> alternatingOrderElements) {
        if (syncActionAndPosition.position.threadIndex == otherBlock.syncActionAndPosition.position.threadIndex) {
            return;
        }
        syncActionAndPosition.syncAction.createOrder(otherBlock.syncActionAndPosition.syncAction,
                fixedOrderElements, alternatingOrderElements,
                syncActionAndPosition.position, otherBlock.syncActionAndPosition.position);
    }
}
