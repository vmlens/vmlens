package com.vmlens.report.assertion;

import java.util.Objects;

public class LeftBeforeRight {

    private final Position left;
    private final Position right;

    public LeftBeforeRight(Position left, Position right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "LeftBeforeRight{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LeftBeforeRight that = (LeftBeforeRight) o;

        if (!Objects.equals(left, that.left)) return false;
        return Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        int result = left != null ? left.hashCode() : 0;
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }
}
