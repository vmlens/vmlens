package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;

public interface StrategyAll {

    void onMethodEnter(Object object,
                               int methodId,
                               OrderMap<Long> monitorOrder,
                               ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter);


}
