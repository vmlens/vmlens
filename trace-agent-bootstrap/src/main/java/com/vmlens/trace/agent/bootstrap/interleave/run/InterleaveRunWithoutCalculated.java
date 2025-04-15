package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class InterleaveRunWithoutCalculated implements InterleaveRun {

    private final ActualRun actualRun;

    public InterleaveRunWithoutCalculated(ActualRun actualRun) {
        this.actualRun = actualRun;
    }

    @Override
    public InterleaveInfo after(InterleaveAction interleaveAction) {
        return actualRun.after(interleaveAction);
    }

    @Override
    public TLinkedList<TLinkableWrapper<InterleaveAction>> run() {
        return actualRun.run();
    }

    @Override
    public boolean isActive(int threadIndex) {
        return true;
    }

    @Override
    public Integer activeThreadIndex() {
        return null;
    }

    @Override
    public ActualRun actualRun() {
        return actualRun;
    }
}
