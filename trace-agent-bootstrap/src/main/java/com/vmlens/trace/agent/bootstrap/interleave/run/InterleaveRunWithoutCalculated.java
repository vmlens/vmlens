package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TIntLinkedList;
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
    public boolean isActive(int threadIndex, TIntLinkedList activeThreadIndices) {
        return calculateActiveByPositionInRun(actualRun.positionInRun(),threadIndex,activeThreadIndices);
    }

    @Override
    public Integer activeThreadIndex() {
        return null;
    }

    @Override
    public ActualRun actualRun() {
        return actualRun;
    }

    public static boolean calculateActiveByPositionInRun(int positionInRun,
                                                         int threadIndex,
                                                         TIntLinkedList activeThreadIndices) {
        int position =  positionInRun % activeThreadIndices.size();
        return activeThreadIndices.get(position) == threadIndex;
    }

}
