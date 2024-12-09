package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.WithObjectHashCodeAndOrder;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;

public class SetObjectHashCodeAndOrder<KEY, EVENT extends WithObjectHashCodeAndOrder<KEY>>
        implements SetFieldsStrategy<EVENT> {

    private final OrderMap<KEY> orderMap;

    public SetObjectHashCodeAndOrder(OrderMap<KEY> orderMap) {
        this.orderMap = orderMap;
    }

    @Override
    public void setFields(EVENT event) {
        long hashCode = System.identityHashCode(event);
        event.setObjectHashCode(hashCode);
        int order = orderMap.getAndIncrementOrder(event.keyForOrderMap());
        event.setOrder(order);
    }
}
