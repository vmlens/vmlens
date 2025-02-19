package com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields;

import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.WithObjectHashCodeAndOrder;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;

public class SetObjectHashCodeAndOrder<KEY, EVENT extends WithObjectHashCodeAndOrder<KEY>>
        implements SetFields<EVENT> {

    private final OrderMap<KEY> orderMap;
    private final Object object;

    public SetObjectHashCodeAndOrder(OrderMap<KEY> orderMap, Object object) {
        this.orderMap = orderMap;
        this.object = object;
    }

    @Override
    public void setFields(EVENT event) {
        long hashCode = System.identityHashCode(object);
        event.setObjectHashCode(hashCode);
        int order = orderMap.getAndIncrementOrder(event.keyForOrderMap());
        event.setOrder(order);
    }
}
