package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class BlockBuilderAndCalculatedRunElementContainerFactory {
    public BlockBuilderAndCalculatedRunElementContainer create(TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun) {
        BlockBuilderAndCalculatedRunElementContainer blockBuilderAndCalculatedRunElementContainer = new BlockBuilderAndCalculatedRunElementContainer();
        for(TLinkableWrapper<InterleaveActionWithPositionFactory> syncAction : actualRun) {
            syncAction.element
                    .addToContainer(blockBuilderAndCalculatedRunElementContainer.getPositionAtThreadIndex(syncAction.element.threadIndex()),
                            blockBuilderAndCalculatedRunElementContainer);

        }
        return blockBuilderAndCalculatedRunElementContainer;
    }
}
