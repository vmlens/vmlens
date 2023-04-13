package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;

import java.util.Iterator;

public class InterleaveLoopIterator implements Iterator<CalculatedRun> {

    private final InterleaveLoop container;
    private InterleaveLoopIteratorState state;

    public InterleaveLoopIterator(InterleaveLoop interleaveLoop) {
        this.container = interleaveLoop;
        state =
                new InterleaveLoopIteratorStateInitial(container);
    }

    @Override
    public boolean hasNext() {
        while(state != null) {
        if(state.hasNext()) {
            return true;
        }
        state = state.nextState();
        }
        return false;
    }

    @Override
    public CalculatedRun next() {
        return state.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
