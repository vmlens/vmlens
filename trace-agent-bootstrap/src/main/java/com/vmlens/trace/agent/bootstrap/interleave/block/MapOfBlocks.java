package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.lockOrMonitor.LockOrMonitorKey;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;


public class MapOfBlocks {

    private final MapContainingStack mapContainingStack = new MapContainingStack();
    private final KeyToThreadIdToElementList<Object, DependentBlock> dependentBlocks =
            new KeyToThreadIdToElementList<>();
    private final TLinkedList<TLinkableWrapper<ElementAndPosition<InDependentBlock>>> inDependentBlockList =
            new TLinkedList<>();

    public void addDependent(Object key, DependentBlock dependentBlock) {
        dependentBlocks.put(key, dependentBlock);
    }

    public void addInDependent(ElementAndPosition<InDependentBlock> inDependentBlockElement) {
        inDependentBlockList.add(new TLinkableWrapper<>(inDependentBlockElement));
    }

    public KeyToThreadIdToElementList<Object, DependentBlock> dependentBlocks() {
        return dependentBlocks;
    }

    public TLinkedList<TLinkableWrapper<ElementAndPosition<InDependentBlock>>> inDependentBlocks() {
        return inDependentBlockList;
    }

    public void push(ElementAndPosition<LockOrMonitorEnter> enter) {
        mapContainingStack.push(enter);
    }

    public ElementAndPosition<LockOrMonitorEnter> pop(LockOrMonitorKey forLockOrMonitor) {
        return mapContainingStack.pop(forLockOrMonitor);
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
