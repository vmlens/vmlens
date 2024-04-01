package com.vmlens.trace.agent.bootstrap.callback.state;

public class ObjectIdAndOrder {

    public final int order;
    private final long id;


    public ObjectIdAndOrder(long id, int order) {
        this.id = id;
        this.order = order;
    }
}
