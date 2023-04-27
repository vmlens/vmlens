package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class AlternatingOrderContainerFactory {

    public AlternatingOrderContainer create(TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>> blockBuilderList,
    ThreadIdToElementList<ElementAndPosition<Object>> run) {
        return new AlternatingOrderContainer(new OrderArraysFactory().create(blockBuilderList),run);
    }
}
