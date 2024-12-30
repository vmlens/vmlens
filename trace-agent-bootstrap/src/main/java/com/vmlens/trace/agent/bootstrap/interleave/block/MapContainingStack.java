package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.lockOrMonitor.LockOrMonitorKey;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.map.hash.THashMap;

public class MapContainingStack {

    private final THashMap<LockOrMonitorKey, TLinkedList<TLinkableWrapper<ElementAndPosition<LockOrMonitorEnter>>>> map =
            new THashMap<>();

    public void push(ElementAndPosition<LockOrMonitorEnter> enter) {
        TLinkedList<TLinkableWrapper<ElementAndPosition<LockOrMonitorEnter>>> stack = map.get(enter.element().key());
        if (stack == null) {
            stack = new TLinkedList<>();
            map.put(enter.element().key(), stack);
        }
        stack.add(new TLinkableWrapper<>(enter));
    }

    public ElementAndPosition<LockOrMonitorEnter> pop(LockOrMonitorKey forLockOrMonitor) {
        TLinkedList<TLinkableWrapper<ElementAndPosition<LockOrMonitorEnter>>> stack = map.get(forLockOrMonitor);
        return stack.removeLast().element();
    }

}
