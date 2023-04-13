package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;

import java.util.Iterator;

/**
 * ToDo test
 */
public class InterleaveLoopIteratorStateAlternatingOrderContainer  implements InterleaveLoopIteratorState {
    private final Iterator<CalculatedRun> alternatingOrderContainerIterator;
    private final InterleaveLoop container;
    private CalculatedRun next;
    private CalculatedRun previous;

    public InterleaveLoopIteratorStateAlternatingOrderContainer(Iterator<CalculatedRun> alternatingOrderContainerIterator,
                                                                InterleaveLoop container) {
        this.alternatingOrderContainerIterator = alternatingOrderContainerIterator;
        this.container = container;
    }

    @Override
    public boolean hasNext() {
        if(previous != null) {
            container.addActualRun(previous.actualRun());
            previous = null;
        }
        if( next != null ) {
            return true;
        }
        while(alternatingOrderContainerIterator.hasNext()) {
            next = alternatingOrderContainerIterator.next();
            if(next != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public CalculatedRun next() {
        previous =  next;
        next = null;
        return previous;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }

    @Override
    public InterleaveLoopIteratorState nextState() {
        if(container.alternatingOrderContainerAvailable()) {
            return new InterleaveLoopIteratorStateAlternatingOrderContainer(
                    container.getAndRemoveAlternatingOrderContainer().iterator()
                    ,container);
        }
        return null;
    }
}
