package com.vmlens.trace.agent.bootstrap.interleave.block.dependent;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.LeftBeforeRightPair;
import com.vmlens.trace.agent.bootstrap.interleave.block.OrderArraysBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;

public class StrategyPotentialConstraint implements Strategy {


    private final LockKey firstLock;
    private final LockKey secondLock;

    public StrategyPotentialConstraint(LockKey firstLock, LockKey secondLock) {
        this.firstLock = firstLock;
        this.secondLock = secondLock;
    }

    @Override
    public void addToBuilder(DependentBlock myBlock,
                             DependentBlock blockFromOtherThread,
                             OrderArraysBuilder orderArraysBuilder) {
        orderArraysBuilder.addOptionalConstraint(new LeftBeforeRightPair(
                leftBeforeRight(myBlock,blockFromOtherThread),
                leftBeforeRight(blockFromOtherThread,myBlock) ,
                firstLock,
                secondLock));
    }

    private static LeftBeforeRight leftBeforeRight(DependentBlock first, DependentBlock second ) {
        return new LeftBeforeRight(first.start.position(), second.end.position());
    }
}
