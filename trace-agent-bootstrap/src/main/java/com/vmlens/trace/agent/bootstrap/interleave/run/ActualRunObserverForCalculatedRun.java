package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;

public class ActualRunObserverForCalculatedRun implements ActualRunObserver {

    private final CalculatedRun calculatedRun;

    public ActualRunObserverForCalculatedRun(CalculatedRun calculatedRun) {
        this.calculatedRun = calculatedRun;
    }

    @Override
    public void after(InterleaveActionWithPositionFactory interleaveActionWithPositionFactory) {
        calculatedRun.incrementPositionInThread();
    }
}
