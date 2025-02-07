package com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategy;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;


public class SynchronizedMethodEnterExitStrategy implements MethodEnterExitStrategy {
    @Override
    public void onMethodEnter(Object object, int methodId, OrderMap<Long> monitorOrder,
                                      ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
    }


}
