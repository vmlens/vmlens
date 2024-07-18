package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;

/**
 * hides the algorithm to calculate a run out of an order.
 * <p>
 * left before right means that I can not execute right when I have not
 * reached left.
 * So I have a list of constraints, e.g. the right positions.
 * I can check if a constraint exists for a position,
 * e.g. pos >= right
 * If not i can take this position, To execute the same thread again
 * I can go forward till I have found a constraint, e.g. the maximum so that still
 * pos &lt; right.
 * Than I can remove all constraints with left =&lt; pos
 * ToDo test, really equals?
 */
public class CreateCalculatedRun {
    private final LeftBeforeRight[] currentOrder;
    private final ThreadIndexToElementList<Position> actualRun;

    public CreateCalculatedRun(LeftBeforeRight[] currentOrder,
                               ThreadIndexToElementList<Position> actualRun) {
        this.currentOrder = currentOrder;
        this.actualRun = actualRun.safeClone();
    }

    public CalculatedRun create() {
        Position[] calculatedRunElementArray = new Position[actualRun.elementCount()];
        int currentPosInArray = 0;
        while (!actualRun.isEmpty()) {
            boolean somethingFound = false;
            for (int i = 0; i <= actualRun.maxThreadIndex(); i++) {
                while (!actualRun.isEmptyAtIndex(i) && !constraintFound(actualRun.getAtIndex(i))) {
                    removeConstraints(actualRun.getAtIndex(i));
                    calculatedRunElementArray[currentPosInArray] = actualRun.getAndRemoveAtIndex(i);
                    currentPosInArray++;
                    somethingFound = true;
                }
            }
            if(! somethingFound) {
                return null;
            }
        }
        return new CalculatedRun(calculatedRunElementArray);
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
                if (position.greaterOrEquals(currentOrder[i].left)) {
                    currentOrder[i] = null;
                }
            }
        }
    }
}
