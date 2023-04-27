package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.interleave.block.BlockBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIdToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class BlockBuilderAndCalculatedRunElementContainer {
    public final TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>> runWithPosition = new TLinkedList<>();
    public final ThreadIdToElementList<ElementAndPosition<Object>> run = new ThreadIdToElementList<>();

    public BlockBuilderAndCalculatedRunElementContainer() {
    }

    public void add(ElementAndPosition<InterleaveAction> withPosition) {
        run.add(new ElementAndPosition(withPosition.element(),withPosition.position()));
        runWithPosition.add(new TLinkableWrapper(new ElementAndPosition(withPosition.element(),withPosition.position())));
    }

    public int getPositionAtThreadIndex(int threadIndex) {
        return run.getPositionAtThreadIndex(threadIndex);
    }
}
