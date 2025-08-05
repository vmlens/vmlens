package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;

import java.util.Objects;

public class AlternativeTwoOrders implements OrderAlternative {

    private final LeftBeforeRight firstOrder;
    private final LeftBeforeRight secondOrder;

    public AlternativeTwoOrders(LeftBeforeRight firstOrder, LeftBeforeRight secondOrder) {
        this.firstOrder = firstOrder;
        this.secondOrder = secondOrder;
    }

    @Override
    public boolean process(CreateOrderContext context) {
        context.addOrder(firstOrder);
        context.addOrder(secondOrder);
        return true;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;

        AlternativeTwoOrders that = (AlternativeTwoOrders) object;
        return Objects.equals(firstOrder, that.firstOrder) && Objects.equals(secondOrder, that.secondOrder);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(firstOrder);
        result = 31 * result + Objects.hashCode(secondOrder);
        return result;
    }
}
