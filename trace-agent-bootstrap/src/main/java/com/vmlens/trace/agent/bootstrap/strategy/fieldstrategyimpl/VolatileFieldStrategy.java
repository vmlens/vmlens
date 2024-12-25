package com.vmlens.trace.agent.bootstrap.strategy.fieldstrategyimpl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionStartAtomicVolatileFieldAccess;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.SetObjectHashCodeAndOrder;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ObjectHashCodeAndFieldId;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.VolatileAccessEvent;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;
import com.vmlens.trace.agent.bootstrap.strategy.FieldStrategy;

public class VolatileFieldStrategy implements FieldStrategy {
    @Override
    public void onAccess(Object fromObject, int fieldId, int position, int inMethodId, int memoryAccessType,
                         OrderMap<ObjectHashCodeAndFieldId> volatileFieldOrder,
                         ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        VolatileAccessEvent volatileAccessEvent = new VolatileAccessEvent();
        volatileAccessEvent.setFieldId(fieldId);
        volatileAccessEvent.setMethodId(inMethodId);
        volatileAccessEvent.setOperation(memoryAccessType);

        CallbackAction callbackAction = CallbackActionStartAtomicVolatileFieldAccess.of(volatileAccessEvent,
                new SetObjectHashCodeAndOrder<>(volatileFieldOrder, fromObject));

        threadLocalWhenInTestAdapter.process(callbackAction);
    }

    @Override
    public void onStaticAccess(int fieldId, int position, int inMethodId, int memoryAccessType,
                               OrderMap<Integer> staticVolatileFieldOrder,
                               ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {

    }
}
