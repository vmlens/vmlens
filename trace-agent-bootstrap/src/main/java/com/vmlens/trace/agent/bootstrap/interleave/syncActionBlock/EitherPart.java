package com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRightOld;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElementOld;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public interface EitherPart<LEFT,RIGHT> {
    void createOrderLeft(Position myPosition, LEFT other,
                         Position otherPosition,
                         DeadlockContext deadlockContext,
                         TLinkedList<TLinkableWrapper<LeftBeforeRightOld>> fixedOrderElements,
                         TLinkedList<TLinkableWrapper<AlternatingOrderElementOld>> alternatingOrderElements);
    void createOrderRight(Position myPosition, RIGHT other,
                          Position otherPosition,
                         DeadlockContext deadlockContext,
                         TLinkedList<TLinkableWrapper<LeftBeforeRightOld>> fixedOrderElements,
                         TLinkedList<TLinkableWrapper<AlternatingOrderElementOld>> alternatingOrderElements);

}
