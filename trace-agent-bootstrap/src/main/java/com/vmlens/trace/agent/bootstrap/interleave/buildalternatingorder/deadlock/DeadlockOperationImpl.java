package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.deadlock;


import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.activelock.LockStartOperation;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeTwoOrders;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.Choice;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.EitherInChoice;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.interleavetypes.DeadlockOperation;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lockcontainer.BlockBlockTuple;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.map.hash.THashMap;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

/**
 * A deadlock consists of 4 monitor enter operations
 *    a1  b1
 *    b2  a2
 *
 *    a1 b2  b1  b2
 *    or
 *    b2 a1  a2  b1
 *
 *    a1 before b2 and
 *    b2 before b1
 *
 *
 */

public class DeadlockOperationImpl implements DeadlockOperation {

    private final PositionPair first;
    private final PositionPair second;
    private final TLinkedList<TLinkableWrapper<BlockBlockTuple>> alternativeBlocks = new TLinkedList<>();


    public DeadlockOperationImpl(PositionPair first, PositionPair second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public void addToMap(THashMap<Position, DeadlockOperation> map) {
        map.put(first.parent(),this);
        map.put(first.child(),this);
        map.put(second.parent(),this);
        map.put(first.child(),this);
    }

    @Override
    public boolean isInDeadlock(BlockBlockTuple potential) {
       if(isPartOfDeadlock(potential.first().start(), potential.second().start(),potential)) {
           return true;
       }
        if(isPartOfDeadlock(potential.second().start(), potential.first().start(),potential)) {
            return true;
        }

        return false;
    }

    public boolean isPartOfDeadlock(LockStartOperation firstStart, LockStartOperation secondStart, BlockBlockTuple potential ) {
        if(firstStart.position().equals(first.parent()) && secondStart.position().equals(second.child())) {
            alternativeBlocks.add(wrap(potential));
            return true;
        }
        if(firstStart.position().equals(first.child()) && secondStart.position().equals(second.parent())) {
            alternativeBlocks.add(wrap(potential));
            return true;
        }
        return false;
    }

    @Override
    public TreeBuilderNode addToAlternatingOrder(TreeBuilderNode node) {
        AlternativeTwoOrders alternativeA = new AlternativeTwoOrders(lbr(first.parent(),second.parent()),
                lbr(second.parent(),first.child()));
        AlternativeTwoOrders alternativeB = new AlternativeTwoOrders(lbr(second.parent(),first.parent()),
                lbr(first.parent(),second.child()));

        Choice choice = node.choice();

        choice.alternativeA().either(alternativeA,alternativeB);

        EitherInChoice nextNode = choice.alternativeB();
        for(TLinkableWrapper<BlockBlockTuple> block: alternativeBlocks) {
            nextNode = block.element().addWhenInDeadlock(nextNode);
        }

        return choice.next();
    }
}
