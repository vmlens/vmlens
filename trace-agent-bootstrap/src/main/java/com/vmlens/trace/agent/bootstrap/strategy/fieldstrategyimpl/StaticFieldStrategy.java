package com.vmlens.trace.agent.bootstrap.strategy.fieldstrategyimpl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ObjectHashCodeAndFieldId;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;
import com.vmlens.trace.agent.bootstrap.strategy.FieldStrategy;

public class StaticFieldStrategy implements FieldStrategy {
    @Override
    public void onAccess(Object fromObject, int fieldId, int position, int inMethodId, int memoryAccessType,
                         OrderMap<ObjectHashCodeAndFieldId> volatileFieldOrder,
                         ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {

    }

    @Override
    public void onStaticAccess(int fieldId, int position, int inMethodId, int memoryAccessType,
                               OrderMap<Integer> staticVolatileFieldOrder,
                               ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {

    }
}
