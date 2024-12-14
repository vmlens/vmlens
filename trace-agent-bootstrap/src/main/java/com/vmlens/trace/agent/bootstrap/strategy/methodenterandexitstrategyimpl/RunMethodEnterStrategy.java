package com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategyimpl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;
import com.vmlens.trace.agent.bootstrap.strategy.MethodEnterStrategy;

public class RunMethodEnterStrategy implements MethodEnterStrategy {
    @Override
    public void onMethodEnter(Object object,
                                      int methodId,
                                      OrderMap<Long> monitorOrder,
                                      ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {

    }

    @Override
    public void onStaticMethodEnter(int methodId,
                                            OrderMap<Integer> staticMonitorOrder,
                                            ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
    }
}
