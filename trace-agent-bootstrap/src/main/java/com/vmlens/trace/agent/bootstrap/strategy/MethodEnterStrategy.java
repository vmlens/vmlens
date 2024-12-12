package com.vmlens.trace.agent.bootstrap.strategy;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;

public interface MethodEnterStrategy {

    OnMethodExit onMethodEnter(Object object,
                               int methodId,
                               OrderMap<Long> monitorOrder,
                               ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter);

    OnMethodExit onStaticMethodEnter(int methodId,
                                     OrderMap<Integer> staticMonitorOrder,
                                     ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter);

}
