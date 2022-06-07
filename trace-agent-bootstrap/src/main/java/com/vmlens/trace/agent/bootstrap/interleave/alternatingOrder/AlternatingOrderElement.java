package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;

public class AlternatingOrderElement {
    public  final LeftBeforeRight leftBeforeRight;

    public static AlternatingOrderElement of(Position left, Position right) {
        return new AlternatingOrderElement(new LeftBeforeRight(left,right));
    }

    public AlternatingOrderElement(LeftBeforeRight leftBeforeRight) {
        if (leftBeforeRight.left.threadIndex < leftBeforeRight.right.threadIndex) {
            this.leftBeforeRight = leftBeforeRight;
        } else {
            this.leftBeforeRight = leftBeforeRight.inverse();
        }


    }

    public LeftBeforeRight getOrder(boolean original) {
        if(original) {
            return leftBeforeRight;
        } else {
            return leftBeforeRight.inverse();
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
        return "alternatingOrder" + leftBeforeRight.toString();
    }
}
