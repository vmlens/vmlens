package com.vmlens.trace.agent.bootstrap.interleave;

public class LeftBeforeRight implements Comparable<LeftBeforeRight> {
    public final Position left;
    public final Position right;

    public static LeftBeforeRight of(Position left, Position right) {
        return new LeftBeforeRight(left, right);
    }

    public LeftBeforeRight(Position left, Position right) {
        this.left = left;
        this.right = right;
    }

    public LeftBeforeRight inverse() {
        return new LeftBeforeRight(right, left);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "," + right.toString() + ")";
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
    public int compareTo(LeftBeforeRight other) {
        int compareRight = right.compareTo(other.right);
        if (compareRight != 0) {
            return compareRight;
        }
        return left.compareTo(other.left);
    }
}
