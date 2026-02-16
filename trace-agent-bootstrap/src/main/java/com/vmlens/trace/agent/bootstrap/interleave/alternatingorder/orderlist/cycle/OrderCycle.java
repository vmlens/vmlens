package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.cycle;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;

import java.util.Objects;

public class OrderCycle {

    private final LeftBeforeRight first;
    private final LeftBeforeRight second;

    private boolean firstActivated;
    private boolean secondActivated;

    public OrderCycle(LeftBeforeRight first, LeftBeforeRight second) {
        this.first = first;
        this.second = second;
    }

    public LeftBeforeRight first() {
        return first;
    }

    public LeftBeforeRight second() {
        return second;
    }

    public void reset() {
        firstActivated = false;
        secondActivated = false;
    }

    public void activateFirst() {
        firstActivated = true;
    }

    public void activateSecond() {
        secondActivated = true;
    }

    public boolean isActivated() {
        return firstActivated && secondActivated;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderCycle that = (OrderCycle) o;
        return Objects.equals(first, that.first) && Objects.equals(second, that.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
