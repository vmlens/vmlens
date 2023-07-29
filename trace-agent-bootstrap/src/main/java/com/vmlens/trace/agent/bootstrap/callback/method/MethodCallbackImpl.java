package com.vmlens.trace.agent.bootstrap.callback.method;

import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;

public class MethodCallbackImpl {

    public void methodEnterOwner(int methodId) {
        try {

            CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

            int slidingWindowId = CallbackState.traceMethods(callbackStatePerThread);

            if (CallbackState.isSlidingWindowTrace(slidingWindowId)) {
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

    public void methodExitOwner(int methodId) {
        try {
            CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

            int slidingWindowId = CallbackState.traceMethods(callbackStatePerThread);
            if (CallbackState.isSlidingWindowTrace(slidingWindowId)) {
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

    public void atomicMethodEnterWithCallback(int atomicId, int methodId) {
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

    public void atomicMethodEnterWithoutCallback(int atomicId, int methodId) {
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

    public void atomicMethodExitWithCallback(int atomicId, int methodId) {
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

    public void atomicMethodExitWithoutCallback(int atomicId, int methodId) {
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

    public void methodEnterThreadRun(int methodId) {
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

    public void methodExitThreadRun(int methodId) {
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

    public void methodEnter(int methodId) {
        try {
            CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            methodEnterInternal(methodId, callbackStatePerThread);

        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void methodExit(int methodId) {
        try {
            CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            methodExitInternal(methodId, callbackStatePerThread);
        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    private void methodEnterInternal(int methodId, CallbackStatePerThread callbackStatePerThread) {

        int slidingWindowId = CallbackState.traceMethods(callbackStatePerThread);

        if (CallbackState.isSlidingWindowTrace(slidingWindowId)) {
            callbackStatePerThread.stackTraceDepth++;

            if (callbackStatePerThread.traceMethodCall()) {

                callbackStatePerThread.methodCount++;
                callbackStatePerThread.sendEvent.writeMethodEnterEventGen(slidingWindowId, methodId,
                        callbackStatePerThread.methodCount);

            }
        }
    }

    private void methodExitInternal(int methodId, CallbackStatePerThread callbackStatePerThread) {
        int slidingWindowId = CallbackState.traceMethods(callbackStatePerThread);
        if (CallbackState.isSlidingWindowTrace(slidingWindowId)) {
            if (callbackStatePerThread.traceMethodCall()) {

                callbackStatePerThread.methodCount++;

                callbackStatePerThread.sendEvent.writeMethodExitEventGen(slidingWindowId, methodId,
                        callbackStatePerThread.methodCount);

            }

            callbackStatePerThread.stackTraceDepth--;

        }
    }


    public void doNotInterleaveEnter() {
        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        callbackStatePerThread.doNotInterleave++;


    }


    public void doNotInterleaveExit() {
        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        callbackStatePerThread.doNotInterleave--;


    }


    public void taskMethodEnter() {
        try {
            CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            ParallelizeFacade.taskMethodEnter(callbackStatePerThread);


        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void taskMethodExit() {
        try {
            CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            ParallelizeFacade.taskMethodExit(callbackStatePerThread);
        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void semaphoreAcquireExit() {
        // ToDo what to do here
    }
}
