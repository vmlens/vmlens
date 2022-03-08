package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrderFactory;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public interface EitherPart<LEFT,RIGHT> {
    void createOrderLeft(Position myPosition, LEFT other,
                         Position otherPosition,
                         DeadlockContext deadlockContext,
                         TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrderElements,
                         TLinkedList<TLinkableWrapper<AlternatingOrderElement>> alternatingOrderElements);
    void createOrderRight(Position myPosition, RIGHT other,
                          Position otherPosition,
                         DeadlockContext deadlockContext,
                         TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrderElements,
                         TLinkedList<TLinkableWrapper<AlternatingOrderElement>> alternatingOrderElements);

}
