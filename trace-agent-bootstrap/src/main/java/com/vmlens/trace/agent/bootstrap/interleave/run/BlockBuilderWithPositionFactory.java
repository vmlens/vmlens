package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.BlockBuilder;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.interleave.Position.pos;

public class BlockBuilderWithPositionFactory<T extends BlockBuilderWithThreadIndex> {

    public BlockBuilderAndThreadIndexToPositionLists create(TLinkedList<TLinkableWrapper<T>> actualRun) {
        BlockBuilderAndThreadIndexToPositionLists blockBuilderAndCalculatedRunElementContainer = new BlockBuilderAndThreadIndexToPositionLists();
        for (TLinkableWrapper<T> syncAction : actualRun) {
            add(syncAction.element, blockBuilderAndCalculatedRunElementContainer);
        }
        return blockBuilderAndCalculatedRunElementContainer;
    }

    private void add(BlockBuilderWithThreadIndex element, BlockBuilderAndThreadIndexToPositionLists container) {
        int position = container.getPositionAtThreadIndex(element.threadIndex());
        container.add(new ElementAndPosition<BlockBuilder>(element, pos(element.threadIndex(), position)));
    }
}
