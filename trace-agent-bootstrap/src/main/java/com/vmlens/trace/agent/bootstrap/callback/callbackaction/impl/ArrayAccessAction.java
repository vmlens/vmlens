package com.vmlens.trace.agent.bootstrap.callback.callbackaction.impl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.setfields.SetObjectHashCode;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ArrayAccessEvent;

public class ArrayAccessAction implements CallbackAction {

    private final Object array;
    private final int index;
    private final int inMethodId;
    private final int operation;

    public ArrayAccessAction(Object array,
                             int index,
                             int inMethodId,
                             int operation) {
        this.array = array;
        this.index = index;
        this.inMethodId = inMethodId;
        this.operation = operation;
    }

    @Override
    public void execute(InTestActionProcessor inTestActionProcessor) {
        ArrayAccessEvent arrayAccessEvent = new ArrayAccessEvent();
        arrayAccessEvent.setArrayIndex(index);
        arrayAccessEvent.setMethodId(inMethodId);
        arrayAccessEvent.setOperation(operation);
        InTestAction inTestAction = new RunAfter<>(arrayAccessEvent,
                new SetObjectHashCode<>(array));
        inTestActionProcessor.process(inTestAction);
    }
}
