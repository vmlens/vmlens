package com.vmlens.trace.agent.bootstrap.interleave.syncActionOld;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRightOld;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElementOld;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock.*;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock.Either.left;

/**
 *
 */

public class ThreadEnd implements EitherPart<ThreadEnd,ThreadJoin>, SyncAction {
    @Override
    public void createOrderLeft(Position myPosition, ThreadEnd other, Position otherPosition, DeadlockContext deadlockContext, TLinkedList<TLinkableWrapper<LeftBeforeRightOld>> fixedOrderElements, TLinkedList<TLinkableWrapper<AlternatingOrderElementOld>> alternatingOrderElements) {
        // nothing to do
    }

    @Override
    public void createOrderRight(Position myPosition, ThreadJoin other, Position otherPosition, 
                                 DeadlockContext deadlockContext, 
                                 TLinkedList<TLinkableWrapper<LeftBeforeRightOld>> fixedOrderElements,
                                 TLinkedList<TLinkableWrapper<AlternatingOrderElementOld>> alternatingOrderElements) {
        if(other.joinedThreadIndex == myPosition.threadIndex) {
            fixedOrderElements.add(new TLinkableWrapper<LeftBeforeRightOld>(
                    new LeftBeforeRightOld(myPosition,otherPosition)));
        }
    }

    @Override
    public void createBlock(Position position, boolean moreThanOneThread, DeadlockAndLockContext deadlockAndLockContext, BlockListCollection blockListCollection) {
        OrderElementFactoryAndPosition<Either<ThreadBegin, ThreadStart>> sap = new OrderElementFactoryAndPosition(moreThanOneThread,
                position, left(this));
        blockListCollection.threadJoinAndEnd.add(new SingleElementBlock(sap));
    }
}
