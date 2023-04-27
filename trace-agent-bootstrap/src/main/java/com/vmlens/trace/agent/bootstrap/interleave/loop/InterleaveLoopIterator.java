package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRun;

import java.util.Iterator;

public class InterleaveLoopIterator implements Iterator<InterleaveRun> {

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
    public InterleaveRun next() {
        return state.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
