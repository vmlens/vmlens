package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRunInitial;

public class InterleaveLoopIteratorStateInitial implements InterleaveLoopIteratorState {

    private final InterleaveLoop container;
    private CalculatedRun calculatedRunInitial = new CalculatedRunInitial();
    private boolean firstRun = true;

    public InterleaveLoopIteratorStateInitial(InterleaveLoop container) {
        this.container = container;
    }

    @Override
    public boolean hasNext() {
        if(firstRun) {
            firstRun = false;
            return true;
        }
        return false;
    }

    @Override
    public CalculatedRun next() {
        return calculatedRunInitial;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }

    @Override
    public InterleaveLoopIteratorState nextState() {
        container.addActualRun(calculatedRunInitial.actualRun());
        if(container.alternatingOrderContainerAvailable()) {
            return new InterleaveLoopIteratorStateAlternatingOrderContainer(
                    container.getAndRemoveAlternatingOrderContainer().iterator()
                    ,container);
        }
        return null;
    }
}
