package com.vmlens.trace.agent.bootstrap.interleave;

import com.vmlens.trace.agent.bootstrap.Pair;

public class LeftAfterRight implements PositionOrder{
    public final Position left;
    public final Position right;

    public LeftAfterRight(Position left, Position right) {
        this.left = left;
        this.right = right;
    }

    public LeftBeforeRight inverse() {
        return new LeftBeforeRight(right,left);
    }

    @Override
    public Position left() {
        return left;
    }

    @Override
    public Pair<LeftBeforeRight,LeftBeforeRight> checkHasCycleOrSetMinimum(CheckMinimumPositon checkMinimumPositon) {
        checkMinimumPositon.addLeftAfterRight(this);
        return null;
    }

    @Override
    public boolean isAfter() {
        return true;
    }

    @Override
    public String toString() {
        return "lar(" + left.threadIndex  + "," + left.positionInThread +
                "," + right.threadIndex +  "," + right.positionInThread  + ");";
    }
}
