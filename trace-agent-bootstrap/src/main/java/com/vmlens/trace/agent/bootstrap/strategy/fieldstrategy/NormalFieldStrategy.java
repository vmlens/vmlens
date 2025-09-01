package com.vmlens.trace.agent.bootstrap.strategy.fieldstrategy;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.setfields.SetFieldsNoOp;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.setfields.SetObjectHashCode;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.FieldAccessEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.FieldAccessEventStatic;

public class NormalFieldStrategy implements FieldStrategy {
    @Override
    public void onAccess(Object fromObject, int fieldId, int position, int inMethodId,
                         int memoryAccessType,
                         ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        FieldAccessEvent fieldAccessEvent = new FieldAccessEvent();
        fieldAccessEvent.setFieldId(fieldId);
        fieldAccessEvent.setMethodId(inMethodId);
        fieldAccessEvent.setOperation(memoryAccessType);

        InTestAction inTestAction = new RunAfter<>(fieldAccessEvent,
                new SetObjectHashCode<>(fromObject));

        threadLocalWhenInTestAdapter.process(inTestAction);
    }

    @Override
    public void onStaticAccess(int fieldId, int position, int inMethodId,
                               int memoryAccessType,
                               ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        FieldAccessEventStatic fieldAccessEvent = new FieldAccessEventStatic();
        fieldAccessEvent.setFieldId(fieldId);
        fieldAccessEvent.setMethodId(inMethodId);
        fieldAccessEvent.setOperation(memoryAccessType);

        InTestAction inTestAction = new RunAfter<>(fieldAccessEvent,
                new SetFieldsNoOp<>());

        threadLocalWhenInTestAdapter.process(inTestAction);
    }
}
