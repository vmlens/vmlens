package com.vmlens.trace.agent.bootstrap.strategy;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ObjectHashCodeAndFieldId;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;

public interface FieldStrategy {

    void onAccess(Object fromObject, int fieldId, int position, int inMethodId, int memoryAccessType,
                  OrderMap<ObjectHashCodeAndFieldId> volatileFieldOrder,
                  ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter);

    void onStaticAccess(int fieldId, int position, int inMethodId, int memoryAccessType,
                        OrderMap<Integer> staticVolatileFieldOrder,
                        ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter);

}
