package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessorImpl;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.nomethodaction.AfterMethodCallAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.nomethodaction.BeforeMethodCallAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.methodaction.MethodEnterAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.methodaction.MethodExitAction;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositorySingleton;
import com.vmlens.trace.agent.bootstrap.strategy.MethodStrategyAdapter;
import com.vmlens.trace.agent.bootstrap.strategy.MethodStrategyAdapterPreAnalyzed;

import static com.vmlens.trace.agent.bootstrap.callback.callbackaction.methodaction.MethodEnterAction.methodEnterAction;
import static com.vmlens.trace.agent.bootstrap.callback.callbackaction.methodaction.MethodEnterAction.methodEnterActionWithIntParameter;
import static com.vmlens.trace.agent.bootstrap.callback.callbackaction.methodaction.MethodExitAction.methodExitAction;
import static com.vmlens.trace.agent.bootstrap.callback.callbackaction.methodaction.MethodExitAction.methodExitActionWithReturnValue;


public class PreAnalyzedCallback {

    private static volatile CallbackActionProcessor callbackActionProcessor = new CallbackActionProcessorImpl();
    private static final ReadWriteLockMap readWriteLockMap = ReadWriteLockMap.INSTANCE;
    private static final MethodStrategyAdapter methodStrategyAdapter = new MethodStrategyAdapterPreAnalyzed(readWriteLockMap,
            MethodRepositorySingleton.INSTANCE);

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
        callbackActionProcessor.process(methodEnterAction);
    }

    public static void methodEnterWithIntParam(Object object,
                                               int intParam,
                                               int methodId) {
        MethodEnterAction methodEnterAction = methodEnterActionWithIntParameter(object,
                methodId,intParam,methodStrategyAdapter);
        callbackActionProcessor.process(methodEnterAction);
    }

    public static void methodExit(Object object, int methodId) {
        MethodExitAction methodEnterAction = methodExitAction(object,
                methodId,methodStrategyAdapter);
        callbackActionProcessor.process(methodEnterAction);
    }

    public static void methodExitObjectReturn(Object returnValue, Object object, int methodId) {
        MethodExitAction methodEnterAction = methodExitActionWithReturnValue(object,
                methodId,returnValue,methodStrategyAdapter);
        callbackActionProcessor.process(methodEnterAction);
    }

    // For Test
    public static void setCallbackActionProcessor(CallbackActionProcessor callbackActionProcessor) {
        PreAnalyzedCallback.callbackActionProcessor = callbackActionProcessor;
    }
}
