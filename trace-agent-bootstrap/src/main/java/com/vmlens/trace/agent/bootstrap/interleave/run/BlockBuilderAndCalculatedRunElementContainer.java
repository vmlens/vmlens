package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.BlockBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class BlockBuilderAndCalculatedRunElementContainer {
    public final TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>> runWithPosition = new TLinkedList<>();
    public final ThreadIndexToElementList<Position> run = new ThreadIndexToElementList<>();

    public BlockBuilderAndCalculatedRunElementContainer() {
    }

    public void add(ElementAndPosition<InterleaveAction> withPosition) {
        run.add(withPosition.position());
        runWithPosition.add(new TLinkableWrapper(new ElementAndPosition(withPosition.element(), withPosition.position())));
    }

    public int getPositionAtThreadIndex(int threadIndex) {
        return run.getPositionAtThreadIndex(threadIndex);
    }
}
