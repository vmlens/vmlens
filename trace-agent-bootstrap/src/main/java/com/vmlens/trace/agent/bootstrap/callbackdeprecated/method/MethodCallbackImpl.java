package com.vmlens.trace.agent.bootstrap.callbackdeprecated.method;

import com.vmlens.trace.agent.bootstrap.callbackdeprecated.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.threadlocal.CallbackActionApplyRuntimeEventFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.threadlocal.ParallelizeBridgeForCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.threadlocal.ParallelizeBridgeForCallbackImpl;

import static com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade.parallelize;

public class MethodCallbackImpl {

    private final ParallelizeBridgeForCallback parallelizeBridgeForCallback;

    public MethodCallbackImpl(ParallelizeBridgeForCallback parallelizeBridgeForCallback) {
        this.parallelizeBridgeForCallback = parallelizeBridgeForCallback;
    }

    public void atomicMethodEnterWithCallback(int atomicId, int methodId) {
        try {
            ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();
            // Fixme Callback

        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void atomicMethodEnterWithoutCallback(int atomicId, int methodId) {
        try {
            ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();
            // Fixme Callback
        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void atomicMethodExitWithCallback(int atomicId, int methodId) {
        try {
            ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();
            // Fixme Callback

        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void atomicMethodExitWithoutCallback(int atomicId, int methodId) {
        try {
            // Fixme Callback

        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void methodEnterThreadRun(int methodId) {
        try {
            ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();
            Thread thread = Thread.currentThread();
            parallelize().beginThreadMethodEnter(callbackStatePerThread, new RunnableOrThreadWrapper(thread));
            parallelizeBridgeForCallback.process(new CallbackActionApplyRuntimeEventFactory(
                    new RuntimeEventFactoryMethodEnter(methodId)));
        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void methodExitThreadRun(int methodId) {
        try {
            parallelizeBridgeForCallback.process(new CallbackActionApplyRuntimeEventFactory(
                    new RuntimeEventFactoryMethodExit(methodId)));
        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void methodEnter(int methodId) {
        try {
            parallelizeBridgeForCallback.process(new CallbackActionApplyRuntimeEventFactory(
                    new RuntimeEventFactoryMethodEnter(methodId)));
        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void methodExit(int methodId) {
        try {
            parallelizeBridgeForCallback.process(new CallbackActionApplyRuntimeEventFactory(
                    new RuntimeEventFactoryMethodExit(methodId)));
        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

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
