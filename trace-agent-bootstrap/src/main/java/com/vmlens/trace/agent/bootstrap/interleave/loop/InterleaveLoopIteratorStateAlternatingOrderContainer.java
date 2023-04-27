package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRun;

import java.util.Iterator;

/**
 * ToDo test
 */
public class InterleaveLoopIteratorStateAlternatingOrderContainer  implements InterleaveLoopIteratorState {
    private final Iterator<CalculatedRun> alternatingOrderContainerIterator;
    private final InterleaveLoop container;
    private InterleaveRun next;
    private InterleaveRun previous;

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
            CalculatedRun temp = alternatingOrderContainerIterator.next();
            if(temp != null) {
                next = new InterleaveRun(temp);
                return true;
            } else {
                next = null;
            }
        }
        return false;
    }

    @Override
    public InterleaveRun next() {
        previous =  next;
        next = null;
        if(container.agentLogger().isDebugEnabled()) {
            previous.debug(container.agentLogger());
        }

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
