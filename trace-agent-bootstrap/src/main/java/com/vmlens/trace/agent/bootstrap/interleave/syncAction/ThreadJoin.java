package com.vmlens.trace.agent.bootstrap.interleave.syncAction;


import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.blockFactory.SyncAction;
import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrderFactory.*;
import com.vmlens.trace.agent.bootstrap.interleave.blockFactory.BlockListCollection;
import com.vmlens.trace.agent.bootstrap.interleave.blockFactory.BuildBlockListContext;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.interleave.alternatingOrderFactory.Either.right;

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
                                TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrderElements, 
                                TLinkedList<TLinkableWrapper<AlternatingOrderElement>> alternatingOrderElements) {
        if(otherPosition.threadIndex == joinedThreadIndex) {
            fixedOrderElements.add(new TLinkableWrapper<LeftBeforeRight>(
                    new LeftBeforeRight(otherPosition,myPosition)));
        }
    }

    @Override
    public void createOrderRight(Position myPosition, ThreadJoin other, Position otherPosition, 
                                 DeadlockContext deadlockContext, 
                                 TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrderElements, 
                                 TLinkedList<TLinkableWrapper<AlternatingOrderElement>> alternatingOrderElements) {
        // Nothing to do
    }

    @Override
    public void createBlock(Position position, int activeThreadCount, BuildBlockListContext buildBlockListContext, BlockListCollection blockListCollection) {
        OrderElementFactoryAndPosition<Either<ThreadBegin, ThreadStart>> sap = new OrderElementFactoryAndPosition(activeThreadCount,
                position, right(this));
        blockListCollection.threadJoinAndEnd.add(new SingleElementBlock(sap));
    }
}
