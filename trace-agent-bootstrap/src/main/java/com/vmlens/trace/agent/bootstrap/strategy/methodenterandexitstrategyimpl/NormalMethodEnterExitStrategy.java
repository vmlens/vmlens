package com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategyimpl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionForRuntimeEvent;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.SetFieldsStrategyNoOp;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodEnterEvent;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;
import com.vmlens.trace.agent.bootstrap.strategy.MethodEnterExitStrategy;

public class NormalMethodEnterExitStrategy implements MethodEnterExitStrategy {
    @Override
    public void onMethodEnter(Object object, int methodId, OrderMap<Long> monitorOrder,
                                      ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        threadLocalWhenInTestAdapter.process(new CallbackActionForRuntimeEvent<>(new MethodEnterEvent(methodId),
                new SetFieldsStrategyNoOp<>()));
    }


}
