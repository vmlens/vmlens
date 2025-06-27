package com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection;

import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

public class ElementIterator<ELEMENT> implements Iterator<ELEMENT> {

    private final Iterator<TLinkableWrapper<TLinkedList<TLinkableWrapper<ELEMENT>>>> threadListIterator;
    private Iterator<TLinkableWrapper<ELEMENT>> currentIterator;

    public ElementIterator(Iterator<TLinkableWrapper<TLinkedList<TLinkableWrapper<ELEMENT>>>> threadListIterator) {
        this.threadListIterator = threadListIterator;
        if(threadListIterator.hasNext()) {
            currentIterator = threadListIterator.next().element().iterator();
        }
    }

    @Override
    public boolean hasNext() {
        while(true) {
            if(currentIterator == null) {
                return false;
            }
            if(currentIterator.hasNext()) {
                return true;
            }
            if(! threadListIterator.hasNext()) {
                currentIterator = null;
                return false;
            }
            currentIterator = threadListIterator.next().element().iterator();
        }
    }

    @Override
    public ELEMENT next() {
        return currentIterator.next().element();
    }
}
