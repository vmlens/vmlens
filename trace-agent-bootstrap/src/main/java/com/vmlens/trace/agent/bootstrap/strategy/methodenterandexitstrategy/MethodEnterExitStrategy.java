package com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategy;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;

public interface MethodEnterExitStrategy {

    void onMethodEnter(Object object,
                               int methodId,
                               OrderMap<Long> monitorOrder,
                               ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter);


}
