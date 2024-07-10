package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.BlockBuilder;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.interleave.Position.pos;

public class BlockBuilderWithPositionFactory {

    public BlockBuilderAndCalculatedRunElementContainer create(TLinkedList<TLinkableWrapper<BlockBuilderWithThreadIndex>> actualRun) {
        BlockBuilderAndCalculatedRunElementContainer blockBuilderAndCalculatedRunElementContainer = new BlockBuilderAndCalculatedRunElementContainer();
        for (TLinkableWrapper<BlockBuilderWithThreadIndex> syncAction : actualRun) {
            add(syncAction.element, blockBuilderAndCalculatedRunElementContainer);
        }
        return blockBuilderAndCalculatedRunElementContainer;
    }

    private void add(BlockBuilderWithThreadIndex element, BlockBuilderAndCalculatedRunElementContainer container) {
        int position = container.getPositionAtThreadIndex(element.threadIndex());
        container.add(new ElementAndPosition<BlockBuilder>(element, pos(element.threadIndex(), position)));
    }

}
