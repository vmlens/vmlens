package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;

import java.util.Objects;

public class AlternativeOneOrder implements OrderAlternative {

    private final LeftBeforeRight leftBeforeRight;

    public AlternativeOneOrder(LeftBeforeRight leftBeforeRight) {
        this.leftBeforeRight = leftBeforeRight;
    }

    @Override
    public boolean process(CreateOrderContext context) {
        context.addOrder(leftBeforeRight);
        return true;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;

        AlternativeOneOrder that = (AlternativeOneOrder) object;
        return Objects.equals(leftBeforeRight, that.leftBeforeRight);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(leftBeforeRight);
    }

    @Override
    public boolean createOrder(LeftBeforeRight orderInCycle) {
        return orderInCycle.equals(leftBeforeRight);
    }
}
