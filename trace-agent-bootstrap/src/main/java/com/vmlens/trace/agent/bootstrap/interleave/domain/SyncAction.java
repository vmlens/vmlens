package com.vmlens.trace.agent.bootstrap.interleave.domain;

import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public interface SyncAction {
    void createOrder(SyncAction other,
                     TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrderElements,
                     TLinkedList<TLinkableWrapper<AlternatingOrderElement>> alternatingOrderElements,
                     Position myPosition,
                     Position otherPosition);
    void accept(SyncActionVisitor visitor);
}
