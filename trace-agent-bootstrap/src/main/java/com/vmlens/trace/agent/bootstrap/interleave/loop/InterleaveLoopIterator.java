package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;

import java.util.Iterator;

public class InterleaveLoopIterator implements Iterator<CalculatedRun> {

    private final IteratorQueue container;
    private Iterator<CalculatedRun> currentIterator;
    private CalculatedRun next;

    public InterleaveLoopIterator(IteratorQueue container) {
        this.container = container;
    }

    @Override
    public boolean hasNext() {
        if (next != null) {
            return true;
        }
        if (currentIterator == null) {
            currentIterator = container.poll();
        }
        while (currentIterator != null) {
            while (currentIterator.hasNext()) {
                CalculatedRun temp = currentIterator.next();
                if (temp != null) {
                    next = temp;
                    return true;
                }
            }
            currentIterator = container.poll();
        }
        return false;
    }

    @Override
    public CalculatedRun next() {
        CalculatedRun temp = next;
        next = null;
        return temp;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
