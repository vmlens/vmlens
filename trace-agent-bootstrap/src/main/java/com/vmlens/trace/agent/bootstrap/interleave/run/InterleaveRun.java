package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TIntLinkedList;
import gnu.trove.list.linked.TLinkedList;

public interface InterleaveRun {

    InterleaveInfo after(InterleaveAction interleaveAction);
    TLinkedList<TLinkableWrapper<InterleaveAction>> run();

    boolean isActive(int threadIndex, TIntLinkedList activeThreadIndices);
    Integer activeThreadIndex(TIntLinkedList activeThreadIndices);

    ActualRun actualRun();
    boolean withCalculated();

}
