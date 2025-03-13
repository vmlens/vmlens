package com.vmlens.trace.agent.bootstrap.interleave.deadlock;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.dependent.DependentBlock;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;

import static com.vmlens.trace.agent.bootstrap.interleave.block.dependent.DependentBlock.createOptionalConstraint;


public class PositionPair {

    private final Position parent;
    private final Position child;

    public PositionPair(Position parent, Position child) {
        this.parent = parent;
        this.child = child;
    }

    public void addToDependentBlockList(LockPair lockPair, ThreadIndexToElementList<DependentBlock> dependentBlockList) {
        dependentBlockList.add(createOptionalConstraint(lockPair.parent(),lockPair.child(),
                new ElementAndPosition<>(new DeadlockDependentBlockElement(lockPair.parent()),parent),
                new ElementAndPosition<>(new DeadlockDependentBlockElement(lockPair.child()),child)));
    }
}
