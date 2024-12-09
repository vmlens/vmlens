package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

public interface WithObjectHashCodeAndOrder<KEY> extends WithObjectHashCode {

    KEY keyForOrderMap();

    void setOrder(int order);

}
