package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRightOld;
import com.vmlens.trace.agent.bootstrap.interleave.Position;

/**
 * should propably be an interface
 */
public class AlternatingOrderElementOld {
    public  final LeftBeforeRightOld leftBeforeRight;

    public static AlternatingOrderElementOld of(Position left, Position right) {
        return new AlternatingOrderElementOld(new LeftBeforeRightOld(left,right));
    }

    public AlternatingOrderElementOld(LeftBeforeRightOld leftBeforeRight) {
        if (leftBeforeRight.left.threadIndex < leftBeforeRight.right.threadIndex) {
            this.leftBeforeRight = leftBeforeRight;
        } else {
            this.leftBeforeRight = leftBeforeRight.inverse();
        }


    }

    public LeftBeforeRightOld getOrder(boolean original) {
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

        AlternatingOrderElementOld that = (AlternatingOrderElementOld) o;
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
