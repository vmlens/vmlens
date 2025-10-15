package com.vmlens.trace.agent.bootstrap.callback.callbackaction.nomethodaction;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.OnAfterMethodCall;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class AfterFieldAccessAction extends NoMethodAction {

    private final ReadWriteLockMap readWriteLockMap;

    public AfterFieldAccessAction(ReadWriteLockMap readWriteLockMap) {
        this.readWriteLockMap = readWriteLockMap;
    }

    @Override
    public void execute(InTestActionProcessor inTestActionProcessor) {
        inTestActionProcessor.process( new OnAfterMethodCall(-1, -1,readWriteLockMap));
    }
}
