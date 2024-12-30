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
     * @return
     */
    public InterleaveInfo after(InterleaveAction interleaveAction) {
        run.add(new TLinkableWrapper(interleaveAction));
        actualRunObserver.after(interleaveAction);
        InterleaveInfo interleaveInfo = new InterleaveInfo(positionInRun);
        positionInRun++;
        return interleaveInfo;
    }

    public int positionInRun() {
        return positionInRun;
    }

    public TLinkedList<TLinkableWrapper<InterleaveAction>> run() {
        return run;
    }




}
