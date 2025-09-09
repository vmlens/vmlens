package com.vmlens.trace.agent.bootstrap.callback.callbackaction.impl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.strategy.MethodContext;
import com.vmlens.trace.agent.bootstrap.strategy.MethodStrategyAdapter;

import static com.vmlens.trace.agent.bootstrap.strategy.MethodContext.methodExitContext;

public class MethodExitAction implements CallbackAction  {

    private final Object object;
    private final int methodId;
    private final Object returnValue;
    private final MethodStrategyAdapter methodStrategyAdapter;


    private MethodExitAction(Object object,
                            int methodId,
                            Object returnValue,
                            MethodStrategyAdapter methodStrategyAdapter) {
        this.object = object;
        this.methodId = methodId;
        this.returnValue = returnValue;
        this.methodStrategyAdapter = methodStrategyAdapter;
    }

    public static MethodExitAction methodExitAction(Object object,
                                                    int methodId,
                                                    MethodStrategyAdapter methodStrategyAdapter) {
        return new MethodExitAction(object,methodId,null,methodStrategyAdapter);
    }

    public static MethodExitAction methodExitActionWithReturnValue(Object object,
                                                                   int methodId,
                                                                   Object returnValue,
                                                                   MethodStrategyAdapter methodStrategyAdapter) {
        return new MethodExitAction(object,methodId,returnValue,methodStrategyAdapter);
    }

    @Override
    public void execute(InTestActionProcessor inTestActionProcessor) {
        MethodContext context = methodExitContext(object,methodId,returnValue,inTestActionProcessor);
        methodStrategyAdapter.methodExit(context);
    }

}
