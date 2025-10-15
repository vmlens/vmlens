package com.vmlens.trace.agent.bootstrap.callback.callbackaction.methodaction;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CheckIsThreadRun;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.strategy.MethodContext;
import com.vmlens.trace.agent.bootstrap.strategy.MethodStrategyAdapter;

import static com.vmlens.trace.agent.bootstrap.strategy.MethodContext.methodEnterContext;

public class MethodEnterAction implements CallbackAction {

    private final Object object;
    private final int methodId;
    private final Integer intParameter;
    private final MethodStrategyAdapter methodStrategyAdapter;

    private MethodEnterAction(Object object,
                             int methodId,
                             Integer intParameter,
                             MethodStrategyAdapter methodStrategyAdapter) {
        this.object = object;
        this.methodId = methodId;
        this.intParameter = intParameter;
        this.methodStrategyAdapter = methodStrategyAdapter;
    }

    public static MethodEnterAction methodEnterAction(Object object,
                                                      int methodId,
                                                      MethodStrategyAdapter methodStrategyAdapter) {
        return new MethodEnterAction(object,methodId,null,methodStrategyAdapter);
    }

    public static MethodEnterAction methodEnterActionWithIntParameter(Object object,
                                                      int methodId,
                                                      int intParameter,
                                                      MethodStrategyAdapter methodStrategyAdapter) {
        return new MethodEnterAction(object,methodId,intParameter,methodStrategyAdapter);
    }

    @Override
    public void execute(InTestActionProcessor inTestActionProcessor) {
        MethodContext context = methodEnterContext(object,methodId,intParameter,inTestActionProcessor);
        methodStrategyAdapter.methodEnter(context);
    }

    @Override
    public Integer isFirstMethodInThread(CheckIsThreadRun checkIsThreadRun) {
        if(checkIsThreadRun.isThreadRun()) {
            return methodId;
        }
        return null;
    }

    @Override
    public boolean couldBeLastMethodInThread(ThreadLocalWhenInTest dataWhenInTest) {
        return false;
    }

    @Override
    public RuntimeEvent isLastMethodInThread(ThreadLocalWhenInTest dataWhenInTest, int stackTraceDepth) {
        return null;
    }
}
