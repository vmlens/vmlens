package com.vmlens.trace.agent.bootstrap.interleave.block.dependent;

import com.vmlens.trace.agent.bootstrap.interleave.WithThreadIndex;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.OrderArraysBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;

/**
 * if we have a single element, start and end are the same
 */
public class DependentBlock implements WithThreadIndex {

    private final Strategy strategy;
    // to be visible in the strategy
    final ElementAndPosition<DependentBlockElement> start;
    final ElementAndPosition<DependentBlockElement> end;

    public DependentBlock(Strategy strategy,
                          ElementAndPosition<DependentBlockElement> start,
                          ElementAndPosition<DependentBlockElement> end) {
        this.strategy = strategy;
        this.start = start;
        this.end = end;
    }

    public DependentBlock(ElementAndPosition<DependentBlockElement> start,
                          ElementAndPosition<DependentBlockElement> end) {
        this(new StrategyAlternating(),start,end);
    }

    public static DependentBlock createOptionalConstraint(LockKey firstLock, LockKey secondLock,
                                                          ElementAndPosition<DependentBlockElement> start,
                                                          ElementAndPosition<DependentBlockElement> end) {
        return new DependentBlock(new StrategyPotentialConstraint(firstLock,secondLock),start,end);
    }


    @Override
    public int threadIndex() {
        return start.threadIndex();

    }

    public void addAlternatingOrder(DependentBlock blockFromOtherThread,
                                    OrderArraysBuilder orderArraysBuilder) {
        if (end.element().startsAlternatingOrder(blockFromOtherThread.start.element())) {
            strategy.addToBuilder(this,blockFromOtherThread,orderArraysBuilder);
            }
    }

    // Visible for Test
    public ElementAndPosition<DependentBlockElement> start() {
        return start;
    }

    // Visible for Test
    public ElementAndPosition<DependentBlockElement> end() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DependentBlock that = (DependentBlock) o;

        if (!start.equals(that.start)) return false;
        return end.equals(that.end);
    }

    @Override
    public int hashCode() {
        int result = start.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }
}
