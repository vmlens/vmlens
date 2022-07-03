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
 * Represents the begin of a thread or task. A pseudo element used to parallelize
 * multiple started threads during a test run.
 * Is the first sync action of a thread for newly started threads.
 * <p>Order:
 * <ul>
 *     <li>Alternate order with each other ThreadBegin</li>
 *     <li>Fixed order with ThreadStart</li>
 * </ul>
 */

public class ThreadBegin implements EitherPart<ThreadBegin,ThreadStart>, SyncAction {

    @Override
    public void createOrderLeft(Position myPosition, ThreadBegin other, Position otherPosition,
                                DeadlockContext deadlockContext,
                                TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrderElements,
                                TLinkedList<TLinkableWrapper<AlternatingOrderElement>> alternatingOrderElements) {
        alternatingOrderElements.add(new TLinkableWrapper<AlternatingOrderElement>(
                new AlternatingOrderElement(new LeftBeforeRight(myPosition, otherPosition))));
    }

    @Override
    public void createOrderRight(Position myPosition, ThreadStart other, Position otherPosition,
                                 DeadlockContext deadlockContext,
                                 TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrderElements,
                                 TLinkedList<TLinkableWrapper<AlternatingOrderElement>> alternatingOrderElements) {
        if(other.startedThreadIndex == myPosition.threadIndex) {
            fixedOrderElements.add(new TLinkableWrapper<LeftBeforeRight>(
                    new LeftBeforeRight(myPosition,otherPosition)));
        }
    }

    @Override
    public void createBlock(Position position, boolean moreThanOneThread, BuildBlockListContext buildBlockListContext,
                            BlockListCollection blockListCollection) {
        OrderElementFactoryAndPosition<Either<ThreadBegin, ThreadStart>> sap = new OrderElementFactoryAndPosition(moreThanOneThread,
                position, left(this));
        blockListCollection.threadStartAndBegin.add(new SingleElementBlock(sap));
    }
}
