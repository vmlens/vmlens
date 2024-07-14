package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;


public class MapOfBlocks {
    private final KeyToThreadIdToElementList<Object, DependentBlock> dependentBlocks =
            new KeyToThreadIdToElementList<>();
    private final TLinkedList<TLinkableWrapper<ElementAndPosition<IndependentBlock>>> inDependentBlockList =
            new TLinkedList<>();

    public void addDependent(Object key, DependentBlock dependentBlock) {
        dependentBlocks.put(key, dependentBlock);
    }

    public void addInDependent(ElementAndPosition<IndependentBlock> inDependentBlockElement) {
        inDependentBlockList.add(new TLinkableWrapper<>(inDependentBlockElement));
    }

    public KeyToThreadIdToElementList<Object, DependentBlock> dependentBlocks() {
        return dependentBlocks;
    }

    public TLinkedList<TLinkableWrapper<ElementAndPosition<IndependentBlock>>> inDependentBlocks() {
        return inDependentBlockList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapOfBlocks that = (MapOfBlocks) o;

        if (dependentBlocks != null ? !dependentBlocks.equals(that.dependentBlocks) : that.dependentBlocks != null)
            return false;
        return inDependentBlockList != null ? inDependentBlockList.equals(that.inDependentBlockList) : that.inDependentBlockList == null;
    }

    @Override
    public int hashCode() {
        int result = dependentBlocks != null ? dependentBlocks.hashCode() : 0;
        result = 31 * result + (inDependentBlockList != null ? inDependentBlockList.hashCode() : 0);
        return result;
    }
}
