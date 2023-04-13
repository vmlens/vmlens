package com.vmlens.trace.agent.bootstrap.interleave.calculatedRun;

import com.vmlens.trace.agent.bootstrap.interleave.block.InterleaveActionWithPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class CalculatedRunFromOrder implements CalculatedRun {

    private final TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun =
            new TLinkedList<>();
    private final CalculatedRunElement[] calculatedRunElementArray;
    private int currentPosInArray = 0;

    public CalculatedRunFromOrder(CalculatedRunElement[] calculatedRunElementArray) {
        this.calculatedRunElementArray = calculatedRunElementArray;
    }

    @Override
    public boolean isActive(int threadIndex) {
        return calculatedRunElementArray[currentPosInArray].threadIndex() == threadIndex;
    }

    @Override
    public void after(InterleaveActionWithPositionFactory interleaveActionWithPosition) {
        actualRun.add(new TLinkableWrapper(interleaveActionWithPosition));
        currentPosInArray++;
    }



    @Override
    public TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun() {
        return actualRun;
    }
}
