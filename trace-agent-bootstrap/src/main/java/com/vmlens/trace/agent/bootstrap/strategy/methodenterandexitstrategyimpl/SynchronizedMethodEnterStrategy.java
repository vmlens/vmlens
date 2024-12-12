package com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategyimpl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;
import com.vmlens.trace.agent.bootstrap.strategy.MethodEnterStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.OnMethodExit;

public class SynchronizedMethodEnterStrategy implements MethodEnterStrategy {
    @Override
    public OnMethodExit onMethodEnter(Object object, int methodId, OrderMap<Long> monitorOrder,
                                      ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        return null;
    }

    @Override
    public OnMethodExit onStaticMethodEnter(int methodId, OrderMap<Integer> staticMonitorOrder,
                                            ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        return null;
    }
}
