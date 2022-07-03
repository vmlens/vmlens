package com.vmlens.trace.agent.bootstrap.interleave.syncActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.BuildBlockListContext;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.SyncAction;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock.*;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock.Either.left;

/**
 *
 */

public class ThreadEnd implements EitherPart<ThreadEnd,ThreadJoin>, SyncAction {
    @Override
    public void createOrderLeft(Position myPosition, ThreadEnd other, Position otherPosition, DeadlockContext deadlockContext, TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrderElements, TLinkedList<TLinkableWrapper<AlternatingOrderElement>> alternatingOrderElements) {
        // nothing to do
    }

    @Override
    public void createOrderRight(Position myPosition, ThreadJoin other, Position otherPosition, 
                                 DeadlockContext deadlockContext, 
                                 TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrderElements, 
                                 TLinkedList<TLinkableWrapper<AlternatingOrderElement>> alternatingOrderElements) {
        if(other.joinedThreadIndex == myPosition.threadIndex) {
            fixedOrderElements.add(new TLinkableWrapper<LeftBeforeRight>(
                    new LeftBeforeRight(myPosition,otherPosition)));
        }
    }

    @Override
    public void createBlock(Position position, boolean moreThanOneThread, BuildBlockListContext buildBlockListContext, BlockListCollection blockListCollection) {
        OrderElementFactoryAndPosition<Either<ThreadBegin, ThreadStart>> sap = new OrderElementFactoryAndPosition(moreThanOneThread,
                position, left(this));
        blockListCollection.threadJoinAndEnd.add(new SingleElementBlock(sap));
    }
}
