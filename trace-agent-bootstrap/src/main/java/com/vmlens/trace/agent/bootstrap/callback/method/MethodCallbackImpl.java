package com.vmlens.trace.agent.bootstrap.callback.method;

import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThreadForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

import static com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade.parallelize;

public class MethodCallbackImpl {

    public void atomicMethodEnterWithCallback(int atomicId, int methodId) {
        try {
            CallbackStatePerThreadForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            // Fixme Callback

        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void atomicMethodEnterWithoutCallback(int atomicId, int methodId) {
        try {
            CallbackStatePerThreadForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            // Fixme Callback
        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void atomicMethodExitWithCallback(int atomicId, int methodId) {
        try {
            CallbackStatePerThreadForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            // Fixme Callback

        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void atomicMethodExitWithoutCallback(int atomicId, int methodId) {
        try {
            CallbackStatePerThreadForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            // Fixme Callback

        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void methodEnterThreadRun(int methodId) {
        try {
            CallbackStatePerThreadForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            callbackStatePerThread.stackTraceBasedDoNotTrace++;
            Thread thread = Thread.currentThread();
            // Fixme Callback hier auch methodid
            parallelize().beginThreadMethodEnter(callbackStatePerThread, new RunnableOrThreadWrapper(thread));

        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void methodExitThreadRun(int methodId) {
        try {
            CallbackStatePerThreadForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            methodExitInternal(methodId, callbackStatePerThread);

        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void methodEnter(int methodId) {
        try {
            CallbackStatePerThreadForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            methodEnterInternal(methodId, callbackStatePerThread);

        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void methodExit(int methodId) {
        try {
            CallbackStatePerThreadForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            methodExitInternal(methodId, callbackStatePerThread);
        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    private void methodEnterInternal(int methodId, CallbackStatePerThreadForParallelize callbackStatePerThread) {
        // Fixme Callback
    }

    private void methodExitInternal(int methodId, CallbackStatePerThreadForParallelize callbackStatePerThread) {
        // Fixme Callback
    }


    public void taskMethodEnter() {
        try {
            // Fixme Callback
            // for future
        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void taskMethodExit() {
        try {
            // Fixme Callback
            // for future
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
