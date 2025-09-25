package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class ActualRun {

    private final TLinkedList<TLinkableWrapper<InterleaveAction>> run;
    private final boolean containsLoop;

    public ActualRun(TLinkedList<TLinkableWrapper<InterleaveAction>> run,
                     boolean containsLoop) {
        this.run = run;
        this.containsLoop = containsLoop;
    }

    public TLinkedList<TLinkableWrapper<InterleaveAction>> run() {
        return run;
    }

    public boolean containsLoop() {
        return containsLoop;
    }
}
