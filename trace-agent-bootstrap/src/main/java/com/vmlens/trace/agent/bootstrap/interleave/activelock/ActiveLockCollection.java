package com.vmlens.trace.agent.bootstrap.interleave.activelock;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.ListIterator;

public class ActiveLockCollection {

    private final ThreadIndexToElementList<ElementAndPosition<LockEnter>> threadIndexToLockList =
            new ThreadIndexToElementList<>();


    public void push(ElementAndPosition<LockEnter> enter) {
        threadIndexToLockList.add(enter);
    }

    public ElementAndPosition<LockEnter> pop(int threadIndex, LockKey forLockOrMonitor) {
        TLinkedList<TLinkableWrapper<ElementAndPosition<LockEnter>>> list = threadIndexToLockList.listAt(threadIndex);
        if(list == null) {
            return null;
        }
        ListIterator<TLinkableWrapper<ElementAndPosition<LockEnter>>> iter = list.listIterator(list.size() );
        while(iter.hasPrevious()) {
            TLinkableWrapper<ElementAndPosition<LockEnter>> element = iter.previous();
            if(element.element().element().key().equals(forLockOrMonitor)) {
                iter.remove();
                return element.element();
            }
        }
        return null;
    }

    public TLinkedList<TLinkableWrapper<ElementAndPosition<LockEnter>>> listAt(int threadIndex) {
        return threadIndexToLockList.listAt(threadIndex);
    }
}
