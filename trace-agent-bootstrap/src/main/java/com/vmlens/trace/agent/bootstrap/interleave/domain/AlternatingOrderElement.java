package com.vmlens.trace.agent.bootstrap.interleave.domain;

public class AlternatingOrderElement {
    public  final LeftBeforeRight leftBeforeRight;

    public static AlternatingOrderElement of(Position left, Position right) {
        return new AlternatingOrderElement(new LeftBeforeRight(left,right));
    }

    public AlternatingOrderElement(LeftBeforeRight leftBeforeRight) {
        this.leftBeforeRight = leftBeforeRight;
    }

    public LeftBeforeRight getOrder(boolean original) {
        if(original) {
            return leftBeforeRight;
        } else {
            return new LeftBeforeRight(leftBeforeRight.right , leftBeforeRight.left);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlternatingOrderElement that = (AlternatingOrderElement) o;
        return leftBeforeRight.equals(that.leftBeforeRight);
    }

    @Override
    public int hashCode() {
        return leftBeforeRight.hashCode();
    }

    @Override
    public String toString() {
        return "ao" + leftBeforeRight.toString() ;
    }
}
