package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContext;
import gnu.trove.set.hash.THashSet;

import java.util.Iterator;

public class InterleaveLoopIterator implements Iterator<CalculatedRun> {

    private final InterleaveLoopContext interleaveLoopContext;
    private final THashSet<CalculatedRun> alreadyExecuted = new THashSet<CalculatedRun>();
    private final IteratorQueue container;
    private Iterator<CalculatedRun> currentIterator;
    private CalculatedRun next;

    public InterleaveLoopIterator(InterleaveLoopContext interleaveLoopContext,
                                  IteratorQueue container) {
        this.interleaveLoopContext = interleaveLoopContext;
        this.container = container;
    }

    @Override
    public boolean hasNext() {

        if(alreadyExecuted.size() > interleaveLoopContext.maximumIterations()) {
            interleaveLoopContext.maximumIterationsReached();
            return false;
        }

        if (next != null) {
            return true;
        }
        if (currentIterator == null) {
            currentIterator = container.poll();
        }
        while (currentIterator != null) {
            while (currentIterator.hasNext()) {
                CalculatedRun temp = nextElement();
                if (temp != null ) {
                    next = temp;
                    return true;
                }
            }
            currentIterator = container.poll();
        }
        return false;
    }

    private CalculatedRun nextElement() {
        CalculatedRun temp = currentIterator.next();
        if(temp == null) {
            return null;
        }
        if(alreadyExecuted.contains(temp)) {
            return null;
        }
        CalculatedRun result = temp.copy();
        alreadyExecuted.add(result);
        return result;
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
