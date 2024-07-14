package com.vmlens.trace.agent.bootstrap.interleave;

import static com.vmlens.trace.agent.bootstrap.interleave.Position.pos;

public class LeftBeforeRight implements Comparable<LeftBeforeRight> {
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
        return new LeftBeforeRight(left, right);
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
        return "(" + left +
                "<" + right + ")";
    }

    @Override
    public int compareTo(LeftBeforeRight other) {
        int compareRight = right.compareTo(other.right);
        if (compareRight != 0) {
            return compareRight;
        }
        return left.compareTo(other.left);
    }

    public LeftBeforeRight inverse() {
        return new LeftBeforeRight(right, left);
    }

}
