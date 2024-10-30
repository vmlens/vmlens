package com.vmlens.trace.agent.bootstrap.stack;

import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class StackImpl<ELEMENT> implements CallStack<ELEMENT>,
        LockOrMonitorStack<ELEMENT> {

    private final TLinkedList<TLinkableWrapper<ELEMENT>> list =
            new TLinkedList<>();

    @Override
    public Iterator<ELEMENT> iterator() {
        return null;
    }

    @Override
    public ELEMENT pop() {
        if (list.isEmpty()) {
            return null;
        }
        return list.removeLast().element;
    }

    @Override
    public void push(ELEMENT element) {
        list.addLast(wrap(element));
    }

    @Override
    public ELEMENT backward(int position) {
        return list.get(list.size() - position - 1).element;
    }

    @Override
    public void remove(int count) {
        for (int i = 0; i < count; i++) {
            pop();
        }
    }

    @Override
    public void dup() {
        ELEMENT toBeAdded = list.getLast().element;
        push(toBeAdded);
    }
}
