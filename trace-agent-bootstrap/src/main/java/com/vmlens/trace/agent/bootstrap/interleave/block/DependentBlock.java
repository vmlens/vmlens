package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.WithThreadIndex;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;

/**
 * if we have a single element, start and end are the same
 */
public class DependentBlock implements WithThreadIndex {
    private final ElementAndPosition<DependentBlockElement> start;
    private final ElementAndPosition<DependentBlockElement> end;

    public DependentBlock(ElementAndPosition<DependentBlockElement> start, ElementAndPosition<DependentBlockElement> end) {
        this.start = start;
        this.end = end;
    }

    public static DependentBlock db(ElementAndPosition<DependentBlockElement> start, ElementAndPosition<DependentBlockElement> end) {
        return new DependentBlock(start, end);
    }

    @Override
    public int threadIndex() {
        return start.threadIndex();
    }

    public void addAlternatingOrder(DependentBlock blockFromOtherThread,
                                    OrderArraysBuilder orderArraysBuilder) {
        if (end.element().startsAlternatingOrder(blockFromOtherThread.start.element())) {
            orderArraysBuilder.addAlternatingOrder(new AlternatingOrderElement(
                    new LeftBeforeRight(end.position(), blockFromOtherThread.start.position()),
                    new LeftBeforeRight(blockFromOtherThread.end.position(), start.position())));
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
