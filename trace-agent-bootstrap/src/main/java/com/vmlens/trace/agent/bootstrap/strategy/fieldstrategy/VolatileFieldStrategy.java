package com.vmlens.trace.agent.bootstrap.strategy.fieldstrategy;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.ExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.ExecuteRunAfter;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.SetExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.VolatileFieldAccessEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.VolatileStaticFieldAccessEvent;


public class VolatileFieldStrategy implements FieldStrategy {
    @Override
    public void onAccess(Object fromObject, int fieldId, int position, int inMethodId, int memoryAccessType,
                         InTestActionProcessor inTestActionProcessor) {
        VolatileFieldAccessEvent volatileAccessEvent = new VolatileFieldAccessEvent(fromObject);
        volatileAccessEvent.setBytecodePosition(position);
        volatileAccessEvent.setFieldId(fieldId);
        volatileAccessEvent.setMethodId(inMethodId);
        volatileAccessEvent.setOperation(memoryAccessType);

        ExecuteAfterOperation executeAfterOperation = new ExecuteRunAfter<>(volatileAccessEvent);


        inTestActionProcessor.process(new SetExecuteAfterOperation(executeAfterOperation));
    }

    @Override
    public void onStaticAccess(int fieldId, int position, int inMethodId, int memoryAccessType,
                               InTestActionProcessor inTestActionProcessor) {
        VolatileStaticFieldAccessEvent volatileAccessEvent = new VolatileStaticFieldAccessEvent();
        volatileAccessEvent.setBytecodePosition(position);
        volatileAccessEvent.setFieldId(fieldId);
        volatileAccessEvent.setMethodId(inMethodId);
        volatileAccessEvent.setOperation(memoryAccessType);

        ExecuteAfterOperation executeAfterOperation = new ExecuteRunAfter<>(volatileAccessEvent);
        inTestActionProcessor.process(new SetExecuteAfterOperation(executeAfterOperation));
    }
}
