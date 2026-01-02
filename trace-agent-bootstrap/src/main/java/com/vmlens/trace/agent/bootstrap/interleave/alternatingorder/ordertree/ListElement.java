package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.cycle.ForEachApply;

public interface ListElement extends OrderTreeNode , ForEachApply {

    void setNext(ListElement next);
    ListElement getNextListElement();
    boolean removeBecauseOfCycle();

}
