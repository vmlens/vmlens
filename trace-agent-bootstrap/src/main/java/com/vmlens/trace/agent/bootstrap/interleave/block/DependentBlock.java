package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.WithThreadIndex;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.ElementAndPosition;

/**
 * if we have a single element, start and end are the same
 */
public class DependentBlock implements WithThreadIndex  {
    private final ElementAndPosition<DependentBlockElement> start;
    private final ElementAndPosition<DependentBlockElement> end;
    public DependentBlock(ElementAndPosition<DependentBlockElement> start, ElementAndPosition<DependentBlockElement> end) {
        this.start = start;
        this.end = end;
    }
    @Override
    public int threadIndex() {return start.threadIndex();}

    public void addToAlternatingOrderContainerBuilder(DependentBlock blockFromOtherThread,
                                                      OrderArraysBuilder orderArraysBuilder) {
     if(end.element().startsAlternatingOrder(blockFromOtherThread.start.element())) {
            orderArraysBuilder.addAlternatingOrder(new AlternatingOrderElement(
                    new LeftBeforeRight(end.position(), blockFromOtherThread.start.position()),
                    new LeftBeforeRight(blockFromOtherThread.end.position(), start.position())));
        }
    }
}
