package com.vmlens.trace.agent.bootstrap.callback.callbackaction.impl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.OnAfterMethodCall;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class AfterMethodCallAction  implements CallbackAction  {

    private final int inMethodId;
    private final int position;
    private final ReadWriteLockMap readWriteLockMap;

    public AfterMethodCallAction(int inMethodId, int position, ReadWriteLockMap readWriteLockMap) {
        this.inMethodId = inMethodId;
        this.position = position;
        this.readWriteLockMap = readWriteLockMap;
    }

    @Override
    public void execute(InTestActionProcessor inTestActionProcessor) {
        inTestActionProcessor.process(new OnAfterMethodCall(inMethodId, position,readWriteLockMap));
    }

}
