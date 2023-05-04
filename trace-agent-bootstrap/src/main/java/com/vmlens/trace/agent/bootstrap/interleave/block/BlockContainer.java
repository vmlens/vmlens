package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;


public class BlockContainer {

    private final KeyToThreadIdToElementList<Object, DependentBlock> dependentBlocks =
            new KeyToThreadIdToElementList<>();
    private final TLinkedList<TLinkableWrapper<ElementAndPosition<InDependentBlockElement>>> inDependentBlockList =
            new TLinkedList<>();

    public void addDependent(Object key, DependentBlock dependentBlock) {
        dependentBlocks.put(key, dependentBlock);
    }

    public void addInDependent(ElementAndPosition<InDependentBlockElement> inDependentBlockElement)   {
        inDependentBlockList.add(new TLinkableWrapper<>(inDependentBlockElement));
    }
    public KeyToThreadIdToElementList<Object, DependentBlock> dependentBlocks() {
        return dependentBlocks;
    }

    public TLinkedList<TLinkableWrapper<ElementAndPosition<InDependentBlockElement>>> inDependentBlocks() {
        return inDependentBlockList;
    }
}
