package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class ActualRun {

    private final TLinkedList<TLinkableWrapper<InterleaveAction>> run =
            new TLinkedList<>();

    private int positionInRun;
    private boolean isSingleThreaded = true;

    public InterleaveInfo after(InterleaveAction interleaveAction) {
        if(isSingleThreaded && ! interleaveAction.startsThread()) {
            return null;
        }
        isSingleThreaded = false;
        run.add(new TLinkableWrapper<>(interleaveAction));
        InterleaveInfo interleaveInfo = new InterleaveInfo(positionInRun);
        positionInRun++;
        return interleaveInfo;
    }

    public InterleaveInfo currentInterleaveInfo() {
        if(isSingleThreaded) {
            return null;
        }
        return new InterleaveInfo(positionInRun);
    }

    int positionInRun() {
        return positionInRun;
    }

    public TLinkedList<TLinkableWrapper<InterleaveAction>> run() {
        return run;
    }

}
