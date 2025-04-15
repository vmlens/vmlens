package com.vmlens.trace.agent.bootstrap.strategy.fieldstrategy;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.SetExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeafteroperation.ExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeafteroperation.ExecuteRunAfter;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetFieldsNoOp;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetObjectHashCode;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.VolatileFieldAccessEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.VolatileStaticFieldAccessEvent;


public class VolatileFieldStrategy implements FieldStrategy {
    @Override
    public void onAccess(Object fromObject, int fieldId, int position, int inMethodId, int memoryAccessType,
                         ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        VolatileFieldAccessEvent volatileAccessEvent = new VolatileFieldAccessEvent(fromObject);
        volatileAccessEvent.setBytecodePosition(position);
        volatileAccessEvent.setFieldId(fieldId);
        volatileAccessEvent.setMethodId(inMethodId);
        volatileAccessEvent.setOperation(memoryAccessType);

        ExecuteAfterOperation executeAfterOperation = new ExecuteRunAfter<>(volatileAccessEvent);


        threadLocalWhenInTestAdapter.process(new SetExecuteAfterOperation(executeAfterOperation));
    }

    @Override
    public void onStaticAccess(int fieldId, int position, int inMethodId, int memoryAccessType,
                               ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        VolatileStaticFieldAccessEvent volatileAccessEvent = new VolatileStaticFieldAccessEvent();
        volatileAccessEvent.setBytecodePosition(position);
        volatileAccessEvent.setFieldId(fieldId);
        volatileAccessEvent.setMethodId(inMethodId);
        volatileAccessEvent.setOperation(memoryAccessType);

        ExecuteAfterOperation executeAfterOperation = new ExecuteRunAfter<>(volatileAccessEvent);
        threadLocalWhenInTestAdapter.process(new SetExecuteAfterOperation(executeAfterOperation));
    }
}
