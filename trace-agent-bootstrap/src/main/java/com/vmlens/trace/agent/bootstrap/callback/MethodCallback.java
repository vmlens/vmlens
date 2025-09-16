package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessorImpl;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.impl.AfterMethodCallAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.impl.BeforeMethodCallAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.impl.MethodEnterAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.impl.MethodExitAction;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositorySingleton;
import com.vmlens.trace.agent.bootstrap.strategy.MethodStrategyAdapter;
import com.vmlens.trace.agent.bootstrap.strategy.MethodStrategyAdapterAll;

import static com.vmlens.trace.agent.bootstrap.callback.callbackaction.impl.MethodEnterAction.methodEnterAction;
import static com.vmlens.trace.agent.bootstrap.callback.callbackaction.impl.MethodExitAction.methodExitAction;

public class MethodCallback {

    private static volatile CallbackActionProcessor callbackActionProcessor = new CallbackActionProcessorImpl();
    private static final ReadWriteLockMap readWriteLockMap = ReadWriteLockMap.INSTANCE;
    private static final MethodStrategyAdapter methodStrategyAdapter =
            new MethodStrategyAdapterAll(MethodRepositorySingleton.INSTANCE);

    public static void beforeMethodCall(int inMethodId, int position, int calledMethodId) {
        BeforeMethodCallAction beforeMethodCallAction = new BeforeMethodCallAction(inMethodId,position);
        callbackActionProcessor.process(beforeMethodCallAction);
    }

    public static void afterMethodCall(int inMethodId, int position, int calledMethodId) {
        AfterMethodCallAction afterMethodCallAction = new AfterMethodCallAction(inMethodId,position,readWriteLockMap);
        callbackActionProcessor.process(afterMethodCallAction);
    }

    public static void methodEnter(Object object, int methodId) {
        MethodEnterAction methodEnterAction = methodEnterAction(object,
                methodId,methodStrategyAdapter);
        callbackActionProcessor.processWithCheckNewThread(methodEnterAction);
    }

    public static void methodExit(Object object, int methodId) {
        MethodExitAction methodEnterAction = methodExitAction(object,
                methodId,methodStrategyAdapter);
        callbackActionProcessor.process(methodEnterAction);
    }

    public static void constructorMethodEnter(int methodId) {

    }

    public static void constructorMethodExit(int methodId) {

    }

    // Visible for Test
    public static void setCallbackActionProcessor(CallbackActionProcessor callbackActionProcessor) {
        MethodCallback.callbackActionProcessor = callbackActionProcessor;
    }
}
