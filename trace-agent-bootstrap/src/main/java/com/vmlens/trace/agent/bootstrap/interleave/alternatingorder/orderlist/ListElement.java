package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.cycle.ForEachApply;

public interface ListElement extends OrderListNode, ForEachApply {

    void setNext(ListElement next);
    ListElement getNextListElement();
    boolean removeBecauseOfCycle();

}
