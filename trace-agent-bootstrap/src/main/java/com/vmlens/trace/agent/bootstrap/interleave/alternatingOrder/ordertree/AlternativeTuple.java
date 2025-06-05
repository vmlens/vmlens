package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

public class AlternativeTuple {

    private final OrderAlternative first;
    private final OrderAlternative second;

    public AlternativeTuple(OrderAlternative first, OrderAlternative second) {
        this.first = first;
        this.second = second;
    }

    public OrderAlternative first() {
        return first;
    }

    public OrderAlternative second() {
        return second;
    }
}
