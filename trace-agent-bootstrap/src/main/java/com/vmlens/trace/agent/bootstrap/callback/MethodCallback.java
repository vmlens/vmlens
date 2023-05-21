package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.event.StackTraceEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;


public class MethodCallback {

    public static void sendStackTraceEventIfNeccesary(final CallbackStatePerThread callbackStatePerThread,
                                                      int slidingWindowId) {
        if (!callbackStatePerThread.methodTracingStarted) {

            final StackTraceElement[] stackTraceArray = Thread.currentThread().getStackTrace();

            /**
             * java.lang.Thread getStackTrace Thread.java 105
             * java.anarsoft.trace.agent.bootstrap.callback.MethodCallback
             * sendStackTraceEventIfNeccesary MethodCallback.java 105
             * java.anarsoft.trace.agent.bootstrap.callback.MethodCallback methodEnter
             * MethodCallback.java 105 java.lang.Class getName Class.java 105
             * com.anarsoft.trace.agent.runtime.AgentRuntimeImpl retransform
             * AgentRuntimeImpl.java 105 com.anarsoft.trace.agent.runtime.AgentRuntimeImpl
             * instrument AgentRuntimeImpl.java 105
             */

            if (stackTraceArray.length > StackTraceEvent.MIN_LENGTH) {
                callbackStatePerThread.queueCollection.putDirect(

                        new StackTraceEvent(callbackStatePerThread.threadId, stackTraceArray));
            }

            callbackStatePerThread.methodTracingStarted = true;

        }
    }

    public static void methodEnterOwner(int methodId) {

        try {

            CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

            int slidingWindowId = CallbackState.traceMethods(callbackStatePerThread);

            if (CallbackState.isSlidingWindowTrace(slidingWindowId)) {
                sendStackTraceEventIfNeccesary(callbackStatePerThread, slidingWindowId);

                // callbackStatePerThread.parallizedMethodCount++; // macht dases auf jeden fall
                // getraced wird
                callbackStatePerThread.stackTraceDepth++;

                callbackStatePerThread.methodCount++;
                callbackStatePerThread.sendEvent.writeParallizedMethodEnterEventGen(slidingWindowId, methodId,
                        callbackStatePerThread.methodCount, 0);

            }

        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static void methodExitOwner(int methodId) {
        try {
            CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

            int slidingWindowId = CallbackState.traceMethods(callbackStatePerThread);

            if (CallbackState.isSlidingWindowTrace(slidingWindowId)) {

                sendStackTraceEventIfNeccesary(callbackStatePerThread, slidingWindowId);

                callbackStatePerThread.methodCount++;

                callbackStatePerThread.sendEvent.writeParallizedMethodExitEventGen(slidingWindowId, methodId,
                        callbackStatePerThread.methodCount);

                callbackStatePerThread.stackTraceDepth--;

            }

        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static void atomicMethodEnterWithCallback(int atomicId, int methodId) {
        try {
            CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            ParallelizeFacade.onAtomicMethodEnter(callbackStatePerThread, methodId, true, atomicId);
            methodEnterInternal(methodId, callbackStatePerThread);

        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static void atomicMethodEnterWithoutCallback(int atomicId, int methodId) {
        try {
            CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            ParallelizeFacade.onAtomicMethodEnter(callbackStatePerThread, methodId, false, atomicId);
            methodEnterInternal(methodId, callbackStatePerThread);
        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static void atomicMethodExitWithCallback(int atomicId, int methodId) {
        try {
            CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            ParallelizeFacade.onAtomicMethodExit(callbackStatePerThread, methodId, atomicId, true);
            methodExitInternal(methodId, callbackStatePerThread);

        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static void atomicMethodExitWithoutCallback(int atomicId, int methodId) {
        try {
            CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            ParallelizeFacade.onAtomicMethodExit(callbackStatePerThread, methodId, atomicId, false);
            methodExitInternal(methodId, callbackStatePerThread);

        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static void methodEnterThreadRun(int methodId) {
        /**
         * nur wenn nicht instanceof worker thread
         *
         */

        try {
            CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            callbackStatePerThread.stackTraceBasedDoNotTrace++;
            Thread thread = Thread.currentThread();
            ParallelizeFacade.beginThreadMethodEnter(callbackStatePerThread, new RunnableOrThreadWrapper(thread));
            methodEnterInternal(methodId, callbackStatePerThread);
            callbackStatePerThread.stackTraceBasedDoNotTrace--;

        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static void methodExitThreadRun(int methodId) {
        try {
            CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            callbackStatePerThread.stackTraceBasedDoNotTrace++;
            ParallelizeFacade.beginThreadMethodExit(callbackStatePerThread);
            methodExitInternal(methodId, callbackStatePerThread);
            callbackStatePerThread.stackTraceBasedDoNotTrace--;

        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static void methodEnter(int methodId) {


        try {
            CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            methodEnterInternal(methodId, callbackStatePerThread);

        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static void methodExit(int methodId) {
        try {
            CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            methodExitInternal(methodId, callbackStatePerThread);

        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    private static void methodEnterInternal(int methodId, CallbackStatePerThread callbackStatePerThread) {

        int slidingWindowId = CallbackState.traceMethods(callbackStatePerThread);

        if (CallbackState.isSlidingWindowTrace(slidingWindowId)) {

            sendStackTraceEventIfNeccesary(callbackStatePerThread, slidingWindowId);

            callbackStatePerThread.stackTraceDepth++;

            if (callbackStatePerThread.traceMethodCall()) {

                callbackStatePerThread.methodCount++;
                callbackStatePerThread.sendEvent.writeMethodEnterEventGen(slidingWindowId, methodId,
                        callbackStatePerThread.methodCount);

            }
        }
    }

    private static void methodExitInternal(int methodId, CallbackStatePerThread callbackStatePerThread) {
        int slidingWindowId = CallbackState.traceMethods(callbackStatePerThread);

        if (CallbackState.isSlidingWindowTrace(slidingWindowId)) {

            sendStackTraceEventIfNeccesary(callbackStatePerThread, slidingWindowId);

            if (callbackStatePerThread.traceMethodCall()) {

                callbackStatePerThread.methodCount++;

                callbackStatePerThread.sendEvent.writeMethodExitEventGen(slidingWindowId, methodId,
                        callbackStatePerThread.methodCount);

            }

            callbackStatePerThread.stackTraceDepth--;

        }
    }


    public static void doNotInterleaveEnter() {
        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        callbackStatePerThread.doNotInterleave++;


    }


    public static void doNotInterleaveExit() {
        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        callbackStatePerThread.doNotInterleave--;


    }


    public static void taskMethodEnter() {
        try {
            CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            ParallelizeFacade.taskMethodEnter(callbackStatePerThread);


        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void taskMethodExit() {
        try {
            CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            ParallelizeFacade.taskMethodExit(callbackStatePerThread);
        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void semaphoreAcquireExit() {
        // ToDo what to do here
    }
}
