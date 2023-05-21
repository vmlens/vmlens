package com.vmlens.trace.agent.bootstrap.parallelize.facade;


import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeFacadeImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopContainer;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.ParallelizeLoopFactoryImpl;

import java.util.concurrent.Future;


/**
 * Singelton for ParallelizeFacadeImpl
 */
public class ParallelizeFacade {

    private static final ParallelizeFacadeImpl parallelizeFacadeImpl =
            new ParallelizeFacadeImpl(new ParallelizeLoopContainer(new ParallelizeLoopFactoryImpl()));

    public static void afterFieldAccessVolatile(ThreadLocalWrapper threadLocalWrapper, int fieldId, int operation) {
        parallelizeFacadeImpl.afterFieldAccessVolatile(threadLocalWrapper, fieldId, operation);
    }

    public static void beforeThreadStart(ThreadLocalWrapper threadLocalWrapper, RunnableOrThreadWrapper runnableOrThreadWrapper) {
        parallelizeFacadeImpl.beforeThreadStart(threadLocalWrapper, runnableOrThreadWrapper);
    }

    public static void beginThreadMethodEnter(ThreadLocalWrapper threadLocalWrapper, RunnableOrThreadWrapper beganTask) {
        parallelizeFacadeImpl.beginThreadMethodEnter(threadLocalWrapper, beganTask);
    }

    public static boolean hasNext(ThreadLocalWrapper threadLocalWrapper, Object obj) {
        return parallelizeFacadeImpl.hasNext(threadLocalWrapper, obj);
    }

    public static void close(ThreadLocalWrapper threadLocalWrapper, Object obj) {
        parallelizeFacadeImpl.close(threadLocalWrapper, obj);
    }

    public static void afterLockOperation(CallbackStatePerThread callbackStatePerThread, ParallelizeLock parallelizeLockEnter) {
    }

    public static void beforeExecutorStart(CallbackStatePerThread callbackStatePerThread, Object runnable) {
    }

    public static void beginThreadMethodExit(CallbackStatePerThread callbackStatePerThread) {
    }

    public static void afterFutureGet(CallbackStatePerThread callbackStatePerThread, Future future) {
    }

    public static void onAtomicMethodEnter(CallbackStatePerThread callbackStatePerThread, int methodId, boolean b, int atomicId) {
    }

    public static void onAtomicMethodExit(CallbackStatePerThread callbackStatePerThread, int methodId, int atomicId, boolean b) {
    }

    public static void taskMethodEnter(CallbackStatePerThread callbackStatePerThread) {
    }

    public static void taskMethodExit(CallbackStatePerThread callbackStatePerThread) {
    }

    public static void beforeFieldAccessVolatile(CallbackStatePerThread callbackStatePerThread, long id, int fieldId, int operation) {
    }

    public static void afterVolatileArrayAccess4UnsafeOrVarHandle(CallbackStatePerThread callbackStatePerThread, long offset, int operation) {
    }

    public static void afterFieldAccess4UnsafeOrVarHandle(CallbackStatePerThread callbackStatePerThread, int fieldId, int operation) {
    }

    public static void beforeMonitorExit(CallbackStatePerThread callbackStatePerThread, int id, int methodId, int position) {
    }

    public static void onMonitorEnter(CallbackStatePerThread callbackStatePerThread, int id) {
    }

    public static void beforeMonitorExitStatic(CallbackStatePerThread callbackStatePerThread, int slidingWindowId, int methodId) {
    }
}
