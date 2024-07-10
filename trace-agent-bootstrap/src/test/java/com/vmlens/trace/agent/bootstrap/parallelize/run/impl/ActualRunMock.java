package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRunObserverNoOp;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveInfo;

import java.util.LinkedList;
import java.util.List;

public class ActualRunMock extends ActualRun {

    private final List<InterleaveAction> interleaveActions = new LinkedList<>();
    private final ActualRunMockStrategy actualRunMockStrategy;

    public ActualRunMock(ActualRunMockStrategy actualRunMockStrategy) {
        super(new ActualRunObserverNoOp());
        this.actualRunMockStrategy = actualRunMockStrategy;
    }

    @Override
    public InterleaveInfo after(InterleaveAction interleaveAction) {
        interleaveActions.add(interleaveAction);
        return actualRunMockStrategy.cteateInterleaveInfo();
    }

    public List<InterleaveAction> interleaveActions() {
        return interleaveActions;
    }
}
