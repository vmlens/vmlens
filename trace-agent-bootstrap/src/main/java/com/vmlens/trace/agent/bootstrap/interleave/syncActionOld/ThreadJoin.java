package com.vmlens.trace.agent.bootstrap.interleave.syncActionOld;


import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRightOld;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElementOld;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock.*;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock.Either.right;

/**
 * Described in {@link ThreadEnd}
 */

public class ThreadJoin implements EitherPart<ThreadEnd,ThreadJoin>, SyncAction {
    
    final int joinedThreadIndex;

    public ThreadJoin(int joinedThreadIndex) {
        this.joinedThreadIndex = joinedThreadIndex;
    }

    @Override
    public void createOrderLeft(Position myPosition, ThreadEnd other, Position otherPosition, 
                                DeadlockContext deadlockContext,
                                TLinkedList<TLinkableWrapper<LeftBeforeRightOld>> fixedOrderElements,
                                TLinkedList<TLinkableWrapper<AlternatingOrderElementOld>> alternatingOrderElements) {
        if(otherPosition.threadIndex == joinedThreadIndex) {
            fixedOrderElements.add(new TLinkableWrapper<LeftBeforeRightOld>(
                    new LeftBeforeRightOld(otherPosition,myPosition)));
        }
    }

    @Override
    public void createOrderRight(Position myPosition, ThreadJoin other, Position otherPosition, 
                                 DeadlockContext deadlockContext, 
                                 TLinkedList<TLinkableWrapper<LeftBeforeRightOld>> fixedOrderElements,
                                 TLinkedList<TLinkableWrapper<AlternatingOrderElementOld>> alternatingOrderElements) {
        // Nothing to do
    }

    @Override
    public void createBlock(Position position, boolean moreThanOneThread, DeadlockAndLockContext deadlockAndLockContext, BlockListCollection blockListCollection) {
        OrderElementFactoryAndPosition<Either<ThreadBegin, ThreadStart>> sap = new OrderElementFactoryAndPosition(moreThanOneThread,
                position, right(this));
        blockListCollection.threadJoinAndEnd.add(new SingleElementBlock(sap));
    }
}
