package com.vmlens.trace.agent.bootstrap.strategy.fieldstrategy;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetObjectHashCode;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.FieldAccessEvent;

public class NormalFieldStrategy implements FieldStrategy {
    @Override
    public void onAccess(Object fromObject, int fieldId, int position, int inMethodId,
                         int memoryAccessType,
                         ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        FieldAccessEvent fieldAccessEvent = new FieldAccessEvent();
        fieldAccessEvent.setFieldId(fieldId);
        fieldAccessEvent.setMethodId(inMethodId);
        fieldAccessEvent.setOperation(memoryAccessType);

        CallbackAction callbackAction = new RunAfter<>(fieldAccessEvent,
                new SetObjectHashCode<>(fromObject));

        threadLocalWhenInTestAdapter.process(callbackAction);
    }

    @Override
    public void onStaticAccess(int fieldId, int position, int inMethodId,
                               int memoryAccessType,
                               ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {

    }
}
