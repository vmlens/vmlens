package com.vmlens.trace.agent.bootstrap.interleave;

public class LeftAfterRight implements PositionOrder{
    public final Position left;
    public final Position right;

    public LeftAfterRight(Position left, Position right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Position left() {
        return left;
    }

    @Override
    public boolean checkHasCycleOrSetMinimum(CheckMinimumPositon checkMinimumPositon) {
        // if left after right set minimum value if greater than current minimum value
        checkMinimumPositon.setIfGreater(right);
        return false;
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
