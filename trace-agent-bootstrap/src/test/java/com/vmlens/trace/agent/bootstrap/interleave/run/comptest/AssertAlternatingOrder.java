package com.vmlens.trace.agent.bootstrap.interleave.run.comptest;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class AssertAlternatingOrder {

    private Set<LeftBeforeRight> currentFoundLeft = new HashSet<>();
    private final Set<LeftBeforeRight> expectedLeftBeforeRight;
    private int count;

    AssertAlternatingOrder(Set<LeftBeforeRight> expectedLeftBeforeRight) {
        this.expectedLeftBeforeRight = expectedLeftBeforeRight;
    }

    public void assertAlternatingOrder(AlternatingOrderContainer alternatingOrderContainer) {
        Iterator<CalculatedRun> iter = alternatingOrderContainer.iterator();
        while (iter.hasNext()) {
            count++;

            CalculatedRun calculatedRun = iter.next();
            for (Position pos : calculatedRun.calculatedRunElementArray()) {
                executed(pos);
            }
            currentFoundLeft = new HashSet<>();
        }
    }


    public boolean expectedLeftBeforeRightIsEmpty() {
        return expectedLeftBeforeRight.isEmpty();
    }

    public int count() {
        return count;
    }

    private void executed(Position position) {
        for (LeftBeforeRight left : expectedLeftBeforeRight) {
            if (left.left.equals(position)) {
                currentFoundLeft.add(left);
            }
        }
        Set<LeftBeforeRight> currentFoundLeftView = new HashSet<>(currentFoundLeft);
        for (LeftBeforeRight right : currentFoundLeftView) {
            if (right.right.equals(position)) {
                currentFoundLeft.remove(right);
                expectedLeftBeforeRight.remove(right);
            }
        }
    }

}
