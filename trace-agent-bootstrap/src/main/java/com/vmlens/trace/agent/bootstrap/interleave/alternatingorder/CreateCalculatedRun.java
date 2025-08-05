package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;

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
 */
public class CreateCalculatedRun {
    private final OrderArrayList orderArrayList;
    private final ElementsPerThreadList<Position> actualRun;
    private final Position[] calculatedRunElementArray;


    public CreateCalculatedRun(OrderArrayList orderArrayList,
                               ElementsPerThreadList<Position> actualRun,
                               Position[] calculatedRunElementArray) {
        this.orderArrayList = orderArrayList;
        this.actualRun = actualRun;
        this.calculatedRunElementArray = calculatedRunElementArray;
    }

    public CalculatedRun create() {
        int currentPosInArray = 0;
        while (!actualRun.isEmpty()) {
            boolean somethingFound = false;
            for (ElementsPerThread<Position>   elementsPerThread : actualRun.threadArray()) {
                while (!elementsPerThread.isEmpty() && !constraintFound(elementsPerThread.first())) {
                    removeConstraints(elementsPerThread.first());
                    calculatedRunElementArray[currentPosInArray] = elementsPerThread.removeFirst();
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
        for(int i = 0; i < orderArrayList.length(); i++) {
            LeftBeforeRight constraint = orderArrayList.get(i);
            if(constraint != null) {
                if(position.greaterOrEquals(constraint.right)) {
                    return true;
                }
            }
        }
        return false;
    }
    private void removeConstraints(Position position) {
        for(int i = 0; i < orderArrayList.length(); i++) {
            if(orderArrayList.get(i) != null) {
                if (position.greaterOrEquals(orderArrayList.get(i).left)) {
                    orderArrayList.setToNull(i);
                }
            }
        }
    }
}
