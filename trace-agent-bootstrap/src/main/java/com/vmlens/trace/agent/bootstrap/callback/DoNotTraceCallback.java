package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessorImpl;

/**
 *
 * Used to avoid tracing inside classloader:
 * java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:519)
 *
 * java.lang.NullPointerException: Cannot invoke "com.vmlens.trace.agent.bootstrap.callback.threadlocal.InMethodIdAndPosition.inMethodId()" because "toSet" is null
 * 	at com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetInMethodIdPositionObjectHashCode.setFields(SetInMethodIdPositionObjectHashCode.java:21)
 * 	at com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetInMethodIdPositionObjectHashCode.setFields(SetInMethodIdPositionObjectHashCode.java:9)
 * 	at com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfter.execute(RunAfter.java:25)
 * 	at com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl.process(ThreadLocalWhenInTestAdapterImpl.java:33)
 * 	at com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.MethodWithLockStrategy.methodEnter(MethodWithLockStrategy.java:29)
 * 	at com.vmlens.trace.agent.bootstrap.callback.impl.PreAnalyzedCallbackImpl.methodEnter(PreAnalyzedCallbackImpl.java:39)
 * 	at com.vmlens.trace.agent.bootstrap.callback.PreAnalyzedCallback.methodEnter(PreAnalyzedCallback.java:55)
 * 	at java.base/java.util.concurrent.ConcurrentHashMap.get(ConcurrentHashMap.java)
 * 	at java.base/jdk.internal.loader.BuiltinClassLoader.findLoadedModule(BuiltinClassLoader.java:709)
 * 	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClassOrNull(BuiltinClassLoader.java:653)
 * 	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:634)
 * 	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:182)
 * 	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:519)
 * 	at com.vmlens.test.maven.plugin.TestReentrant.testUpdate(TestReentrant.java:19)
 * 	at java.base/java.lang.reflect.Method.invoke(Method.java:567)
 *
 */


public class DoNotTraceCallback {

    private static volatile CallbackActionProcessor callbackActionProcessor = new CallbackActionProcessorImpl();

    public static void methodEnter(Object object, int methodId) {
        callbackActionProcessor.startDoNotTrace();
    }

    public static void methodExit(Object object, int methodId) {
        callbackActionProcessor.endDoNotTrace();
    }

    // For Test
    public static void setCallbackActionProcessor(CallbackActionProcessor callbackActionProcessor) {
        DoNotTraceCallback.callbackActionProcessor = callbackActionProcessor;
    }
}
