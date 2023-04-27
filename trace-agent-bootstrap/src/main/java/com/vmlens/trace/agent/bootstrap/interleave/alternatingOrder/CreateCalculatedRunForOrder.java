package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIdToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRunFromOrder;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.ElementAndPosition;

/**
 * @hides the algorithm to calculate a run out of an order.
 *
 * left before right means that I can not execute right when I have not
 * reached left.
 * So I have a list of constraints, e.g. the right positions.
 * I can check if a constraint exists for a position,
 * e.g. pos >= right
 * If not i can take this position, To execute the same thread again
 * I can go forward till I have found a constraint, e.g. the maximum so tha still
 * pos < right.
 * Than I can remove all constraints with left =< pos
 * ToDo test, really equals?
 *
 */
public class CreateCalculatedRunForOrder {
    private final LeftBeforeRight[] currentOrder;
    private final ThreadIdToElementList<ElementAndPosition<Object>> actualRun;

    public CreateCalculatedRunForOrder(LeftBeforeRight[] currentOrder,
                                       ThreadIdToElementList<ElementAndPosition<Object>> actualRun) {
        this.currentOrder = currentOrder;
        this.actualRun = actualRun.safeClone();
    }
    public CalculatedRun create() {
        ElementAndPosition<Object>[] calculatedRunElementArray = new ElementAndPosition[actualRun.elementCount()];
        int currentPosInArray = 0;
        while(! actualRun.isEmpty()) {
            boolean somethingFound = false;
            for(int i = 0;i <=  actualRun.maxThreadIndex(); i++) {
                    while(! actualRun.isEmptyAtIndex(i) && ! constraintFound(actualRun.getAtIndex(i).position())) {
                        removeConstraints(actualRun.getAtIndex(i).position());
                        calculatedRunElementArray[currentPosInArray] = actualRun.getAndRemoveAtIndex(i);
                        currentPosInArray++;
                        somethingFound=true;
                    }
            }
            if(! somethingFound) {
                return null;
            }
        }
        return new CalculatedRunFromOrder(calculatedRunElementArray);
    }
    private boolean constraintFound(Position position) {
        for(LeftBeforeRight constraint : currentOrder) {
            if(constraint != null) {
                if(position.greaterOrEquals(constraint.right)) {
                    return true;
                }
            }
        }
        return false;
    }
    private void removeConstraints(Position position) {
        for(int i = 0; i < currentOrder.length; i++) {
            if(currentOrder[i] != null) {
                if(currentOrder[i].left.greaterOrEquals(position)) {
                    currentOrder[i] = null;
                }
            }
        }
    }
}
