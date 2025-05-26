package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

public class AlternativeTuple {

    private final ListNodeAlternative first;
    private final ListNodeAlternative second;

    public AlternativeTuple(ListNodeAlternative first, ListNodeAlternative second) {
        this.first = first;
        this.second = second;
    }

    public ListNodeAlternative first() {
        return first;
    }

    public ListNodeAlternative second() {
        return second;
    }
}
