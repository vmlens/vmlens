package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;

import java.util.Iterator;

public class InterleaveLoopIterator implements Iterator<ActualRun> {

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
    public ActualRun next() {
        return state.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
