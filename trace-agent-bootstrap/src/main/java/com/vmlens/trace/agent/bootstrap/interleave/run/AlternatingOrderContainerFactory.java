package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.BlockBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.block.OrderArraysFactory;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class AlternatingOrderContainerFactory {

    /*
    Fixme
    public AlternatingOrderContainer create(TLinkedList<TLinkableWrapper<InterleaveFactory>> actualRun) {
        BlockBuilderAndCalculatedRunElementContainer container = new BlockBuilderAndCalculatedRunElementContainerFactory().create(actualRun);
        return create(container.runWithPosition, container.run);
    }
*/

    public AlternatingOrderContainer create(TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>> blockBuilderList,
                                            ThreadIndexToElementList<Position> run) {
        return new AlternatingOrderContainer(new OrderArraysFactory().create(blockBuilderList, run), run);
    }
}
