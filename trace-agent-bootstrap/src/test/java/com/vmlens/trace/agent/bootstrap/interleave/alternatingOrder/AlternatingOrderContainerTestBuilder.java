package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.element.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.element.AlwaysEnabled;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;

import java.util.LinkedList;
import java.util.List;

import static com.vmlens.trace.agent.bootstrap.interleave.Position.p;
import static java.lang.Math.max;

public class AlternatingOrderContainerTestBuilder {

    private final List<LeftBeforeRight> fixed = new LinkedList<>();
    private final List<AlternatingOrderElement> alternating = new LinkedList<>();


    public void fixed(Position left, Position right) {
        fixed.add(new LeftBeforeRight(left, right));
    }

    public void both(Position left, Position right) {
        LeftBeforeRight oneRelation = new LeftBeforeRight(left, right);
        alternating.add(new AlternatingOrderElement(new AlwaysEnabled(),oneRelation, oneRelation.inverse()));
    }

    public AlternatingOrderContainer build() {
        LeftBeforeRight[] fa = fixed.toArray(new LeftBeforeRight[0]);
        AlternatingOrderElement[] aoe = alternating.toArray(new AlternatingOrderElement[0]);
        int maxThreadIndex = 0;
        for (LeftBeforeRight lfr : fa) {
            maxThreadIndex = maxThreadIndex(lfr, maxThreadIndex);
        }
        for (AlternatingOrderElement ae : aoe) {
            maxThreadIndex = maxThreadIndex(ae, maxThreadIndex);
        }
        int[] maxPositionToIndex = new int[maxThreadIndex + 1];
        for (LeftBeforeRight lfr : fa) {
            setMaxPosition(lfr, maxPositionToIndex);
        }
        for (AlternatingOrderElement ae : aoe) {
            setMaxPosition(ae, maxPositionToIndex);
        }
        OrderArrays orderArrays = new OrderArrays(fa, aoe,new LeftBeforeRightPair[0]);
        ThreadIndexToElementList<Position> actualRun = new ThreadIndexToElementList<>();
        for (int index = 0; index < maxPositionToIndex.length; index++) {
            for (int position = 0; position <= maxPositionToIndex[index]; position++) {
                actualRun.add(p(index, position));
            }
        }
        return new AlternatingOrderContainer(orderArrays, actualRun);
    }

    private int maxThreadIndex(LeftBeforeRight leftBeforeRight, int currentMax) {
        int left = max(leftBeforeRight.left.threadIndex, currentMax);
        return max(leftBeforeRight.right.threadIndex, left);
    }

    private int maxThreadIndex(AlternatingOrderElement alternatingOrderElement, int currentMax) {
        int first = maxThreadIndex(alternatingOrderElement.order(false), currentMax);
        return maxThreadIndex(alternatingOrderElement.order(true), first);
    }

    private void setMaxPosition(AlternatingOrderElement alternatingOrderElement, int[] maxPositionToIndex) {
        setMaxPosition(alternatingOrderElement.order(false), maxPositionToIndex);
        setMaxPosition(alternatingOrderElement.order(true), maxPositionToIndex);
    }

    private void setMaxPosition(LeftBeforeRight leftBeforeRight, int[] maxPositionToIndex) {
        setMaxPosition(leftBeforeRight.right, maxPositionToIndex);
        setMaxPosition(leftBeforeRight.left, maxPositionToIndex);
    }

    private void setMaxPosition(Position position, int[] maxPositionToIndex) {
        maxPositionToIndex[position.threadIndex] = max(position.positionInThread,
                maxPositionToIndex[position.threadIndex]);
    }

}
