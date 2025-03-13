package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.element.AlternatingOrderElement;

import java.util.Arrays;

public class OrderArrays {
    public final LeftBeforeRight[] fixedOrderArray;
    public final AlternatingOrderElement[] alternatingOrderArray;
    public final LeftBeforeRightPair[] potentialConstraints;

    public OrderArrays(LeftBeforeRight[] fixedOrderArray,
                       AlternatingOrderElement[] alternatingOrderArray,
                       LeftBeforeRightPair[] potentialConstraints) {
        this.fixedOrderArray = fixedOrderArray;
        this.alternatingOrderArray = alternatingOrderArray;
        this.potentialConstraints = potentialConstraints;

        Arrays.sort(this.fixedOrderArray);
        Arrays.sort(this.alternatingOrderArray);
        Arrays.sort(this.potentialConstraints);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        OrderArrays that = (OrderArrays) o;
        return Arrays.equals(fixedOrderArray, that.fixedOrderArray) && Arrays.equals(alternatingOrderArray, that.alternatingOrderArray) && Arrays.equals(potentialConstraints, that.potentialConstraints);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(fixedOrderArray);
        result = 31 * result + Arrays.hashCode(alternatingOrderArray);
        result = 31 * result + Arrays.hashCode(potentialConstraints);
        return result;
    }

    @Override
    public String toString() {
        return "OrderArrays{" +
                "fixedOrderArray=" + Arrays.toString(fixedOrderArray) +
                ", alternatingOrderArray=" + Arrays.toString(alternatingOrderArray) +
                ", potentialConstraints=" + Arrays.toString(potentialConstraints) +
                '}';
    }
}
