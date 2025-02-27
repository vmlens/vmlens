package com.vmlens.trace.agent.bootstrap.strategy.fieldstrategy;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunStartAtomicAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetObjectHashCode;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.VolatileAccessEvent;


public class VolatileFieldStrategy implements FieldStrategy {
    @Override
    public void onAccess(Object fromObject, int fieldId, int position, int inMethodId, int memoryAccessType,
                         ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        VolatileAccessEvent volatileAccessEvent = new VolatileAccessEvent();
        volatileAccessEvent.setFieldId(fieldId);
        volatileAccessEvent.setMethodId(inMethodId);
        volatileAccessEvent.setOperation(memoryAccessType);

        CallbackAction callbackAction = RunStartAtomicAction.of(volatileAccessEvent,
                new SetObjectHashCode<>(fromObject));

        threadLocalWhenInTestAdapter.process(callbackAction);
    }

    @Override
    public void onStaticAccess(int fieldId, int position, int inMethodId, int memoryAccessType,
                               ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {

    }
}
