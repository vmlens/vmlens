package com.vmlens.trace.agent.bootstrap.interleave;

public class LeftBeforeRightOld implements Comparable<LeftBeforeRightOld> {
    public final Position left;
    public final Position right;

    public static LeftBeforeRightOld of(Position left, Position right) {
        return new LeftBeforeRightOld(left, right);
    }

    public LeftBeforeRightOld(Position left, Position right) {
        this.left = left;
        this.right = right;
    }

    public LeftBeforeRightOld inverse() {
        return new LeftBeforeRightOld(right, left);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "," + right.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeftBeforeRightOld that = (LeftBeforeRightOld) o;
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
    public int compareTo(LeftBeforeRightOld other) {
        int compareRight = right.compareTo(other.right);
        if (compareRight != 0) {
            return compareRight;
        }
        return left.compareTo(other.left);
    }
}
