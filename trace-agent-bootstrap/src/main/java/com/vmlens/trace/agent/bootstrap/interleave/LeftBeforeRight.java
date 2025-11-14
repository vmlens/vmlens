package com.vmlens.trace.agent.bootstrap.interleave;

import static com.vmlens.trace.agent.bootstrap.interleave.Position.pos;

public class LeftBeforeRight implements Comparable<LeftBeforeRight> , PositionOrder {
    public final Position left;
    public final Position right;

    public LeftBeforeRight(Position left, Position right) {
        this.left = left;
        this.right = right;
    }

    public static LeftBeforeRight lbr(int leftThreadIndex, int leftPositionInThread,
                                      int rightThreadIndex, int rightPositionInThread) {
        return new LeftBeforeRight(pos(leftThreadIndex, leftPositionInThread), pos(rightThreadIndex, rightPositionInThread));
    }

    public static LeftBeforeRight lbr(Position left,
                                      Position right) {
        return new LeftBeforeRight(left,right);
    }

    public LeftAfterRight inverse() {
        return new LeftAfterRight(right,left);
    }

    public Position left() {
        return left;
    }

    @Override
    public boolean checkHasCycleOrSetMinimum(CheckMinimumPositon checkMinimumPositon) {
        // if left before right check that value is greater than current minimum value
        int minimum = checkMinimumPositon.get(right.threadIndex);
        return minimum > right.positionInThread ;
    }

    @Override
    public boolean isAfter() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LeftBeforeRight that = (LeftBeforeRight) o;

        if (!left.equals(that.left)) return false;
        return right.equals(that.right);
    }

    @Override
    public int hashCode() {
        int result = left.hashCode();
        result = 31 * result + right.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "lbr(" + left.threadIndex  + "," + left.positionInThread +
                "," + right.threadIndex +  "," + right.positionInThread  + ");";
    }

    @Override
    public int compareTo(LeftBeforeRight other) {
        int compareRight = right.compareTo(other.right);
        if (compareRight != 0) {
            return compareRight;
        }
        return left.compareTo(other.left);
    }


}
