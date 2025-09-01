package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.setfields.SetObjectHashCode;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ArrayAccessEvent;

public class ArrayCallbackImpl {

    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;

    public ArrayCallbackImpl(ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
    }

    public void beforeArrayRead(Object array, int index, int inMethodId) {
        onArrayAccess(array,index,inMethodId,MemoryAccessType.IS_READ);
    }

    public void beforeArrayWrite(Object array, int index, int inMethodId) {
        onArrayAccess(array,index,inMethodId,MemoryAccessType.IS_WRITE);
    }

    private void onArrayAccess(Object array, int index, int inMethodId, int operation) {
        ArrayAccessEvent arrayAccessEvent = new ArrayAccessEvent();
        arrayAccessEvent.setArrayIndex(index);
        arrayAccessEvent.setMethodId(inMethodId);
        arrayAccessEvent.setOperation(operation);

        InTestAction inTestAction = new RunAfter<>(arrayAccessEvent,
                new SetObjectHashCode<>(array));

        threadLocalWhenInTestAdapter.process(inTestAction);
    }

}

