package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.Pair;
import com.vmlens.trace.agent.bootstrap.interleave.CheckMinimumPositon;
import com.vmlens.trace.agent.bootstrap.interleave.LeftAfterRight;
import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;

public class TwoEdgesCycleFilterState implements CheckMinimumPositon {

    private final LeftAfterRight[] minimumPositions;

    public TwoEdgesCycleFilterState(int maxThreadIndex) {
        this.minimumPositions = new LeftAfterRight[maxThreadIndex+1];
    }

    @Override
    public void addLeftAfterRight(LeftAfterRight leftAfterRight) {
        // if left after right set minimum value if greater than current minimum value
        LeftAfterRight lar = minimumPositions[leftAfterRight.right.threadIndex];
        if(lar == null) {
            minimumPositions[leftAfterRight.right.threadIndex] = leftAfterRight;
        } else {
            int minimum = minimumPositions[leftAfterRight.right.threadIndex].right.positionInThread;
            if(minimum < leftAfterRight.right.positionInThread) {
                minimumPositions[leftAfterRight.right.threadIndex] = leftAfterRight;
            }
        }
    }

    @Override
    public Pair<LeftBeforeRight, LeftBeforeRight> checkLeftBeforeRight(LeftBeforeRight leftBeforeRight) {
        // if left before right check that value is greater than current minimum value
        LeftAfterRight lar = minimumPositions[leftBeforeRight.right.threadIndex];
        if(lar == null) {
            return null;
        }
        if(lar.right.positionInThread > leftBeforeRight.right.positionInThread) {
            return new Pair<>(leftBeforeRight,lar.inverse());
        }
       return null;
    }
}
