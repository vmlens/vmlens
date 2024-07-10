package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class ActualRun {
    private final ActualRunObserver actualRunObserver;
    private final TLinkedList<TLinkableWrapper<InterleaveAction>> run =
            new TLinkedList<>();

    public ActualRun(ActualRunObserver actualRunObserver) {
        this.actualRunObserver = actualRunObserver;
    }
    private int positionInRun;

    /**
     * @param interleaveAction
     * @return can be null
     */
    public InterleaveInfo after(InterleaveAction interleaveAction) {
        run.add(new TLinkableWrapper(interleaveAction));
        actualRunObserver.after(interleaveAction);
        InterleaveInfo interleaveInfo = new InterleaveInfo(positionInRun);
        positionInRun++;
        return interleaveInfo;
    }


    public TLinkedList<TLinkableWrapper<InterleaveAction>> run() {
        return run;
    }




}
