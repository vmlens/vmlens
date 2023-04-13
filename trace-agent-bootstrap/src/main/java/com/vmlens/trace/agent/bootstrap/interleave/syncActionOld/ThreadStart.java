package com.vmlens.trace.agent.bootstrap.interleave.syncActionOld;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRightOld;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElementOld;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock.*;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock.Either.right;

/**
 * Described in {@link ThreadBegin}
 */

public class ThreadStart implements EitherPart<ThreadBegin, ThreadStart>, SyncAction {
    
    final int startedThreadIndex;

    public ThreadStart(int startedThreadIndex) {
        this.startedThreadIndex = startedThreadIndex;
    }

    @Override
    public void createOrderLeft(Position myPosition, ThreadBegin other, Position otherPosition,
                                DeadlockContext deadlockContext,
                                TLinkedList<TLinkableWrapper<LeftBeforeRightOld>> fixedOrderElements,
                                TLinkedList<TLinkableWrapper<AlternatingOrderElementOld>> alternatingOrderElements) {
        if(startedThreadIndex == otherPosition.threadIndex) {
            fixedOrderElements.add(new TLinkableWrapper<LeftBeforeRightOld>(
                    new LeftBeforeRightOld(myPosition,otherPosition)));
        }

    }

    @Override
    public void createOrderRight(Position myPosition, ThreadStart other, Position otherPosition,
                                 DeadlockContext deadlockContext,
                                 TLinkedList<TLinkableWrapper<LeftBeforeRightOld>> fixedOrderElements,
                                 TLinkedList<TLinkableWrapper<AlternatingOrderElementOld>> alternatingOrderElements) {
        // Nothing to do
    }

    @Override
    public void createBlock(Position position, boolean moreThanOneThreadt, DeadlockAndLockContext deadlockAndLockContext,
                            BlockListCollection blockListCollection) {
        OrderElementFactoryAndPosition<Either<ThreadBegin, ThreadStart>> sap = new OrderElementFactoryAndPosition(moreThanOneThreadt,
                position, right(this));
        blockListCollection.threadStartAndBegin.add(new SingleElementBlock(sap));
    }
}
