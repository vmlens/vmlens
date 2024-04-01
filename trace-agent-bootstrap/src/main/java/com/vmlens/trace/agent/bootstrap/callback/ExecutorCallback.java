package com.vmlens.trace.agent.bootstrap.callback;


import com.vmlens.trace.agent.bootstrap.AtomicClassRepo;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

import java.util.concurrent.*;

import static com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade.parallelize;


public class ExecutorCallback {


    public static void threadStartMethodEnter(Object runnable) {
        // Fixme Callback
        // ParallelizeFacade.beforeExecutorStart(CallbackState.callbackStatePerThread.get(), runnable);

        ThreadLocalForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();


    }


    public static void threadStartMethodExit() {
        ThreadLocalForParallelize callbackStatePerThread =
                CallbackState.callbackStatePerThread.get();


        ParallelizeCallback.afterThreadStart();


    }


    public static void forkJoinTaskForkEnter(ForkJoinTask task) {
        // Fixme Callback
        //ParallelizeFacade.beforeExecutorStart(CallbackState.callbackStatePerThread.get(), task);


    }


    public static void forkJoinTaskForkExit() {
        ThreadLocalForParallelize callbackStatePerThread =
                CallbackState.callbackStatePerThread.get();


        ParallelizeCallback.afterThreadStart();
    }


    /**
     * Für ForkJoinPool und Threads, und Threads die runnables aufrufen
     *
     * @param task
     */


    public static void methodEnterExecTask(Object task) {

        if (task instanceof Thread) {
            ThreadLocalForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();

            parallelize().beginThreadMethodEnter(callbackStatePerThread, new RunnableOrThreadWrapper(task));
        }

    }


    public static void methodExitExecTask(Object task) {

        if (task instanceof Thread) {

            ThreadLocalForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();

// Fixme Callback
            // ParallelizeFacade.beginThreadMethodExit(callbackStatePerThread);

        }
    }


    public static void execAfter() {
        ThreadLocalForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();


        // Fixme Callback
        // ParallelizeFacade.beginThreadMethodExit(callbackStatePerThread);

    }


    public static void execBefore(ForkJoinTask task) {


        ThreadLocalForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();



        parallelize().beginThreadMethodEnter(callbackStatePerThread, new RunnableOrThreadWrapper(task));
        // ToDo do i need this?
        //if (!) {
        //    callbackStatePerThread.notStartedCount++;
        //}
    }


    /*
     * für ThreadPoolExecutor
     *
     *
     * @param runnable
     * @param methodId
     * @throws Exception
     */

    public static Object call(Callable callable, int methodId) throws Exception {
        int atomicId = AtomicClassRepo.getId4AtomicClass("java/util/concurrent/FutureTask");
        // Fixme Callback
        //parallelize().callbackMethodEnter(atomicId);

        try {
            return callable.call();
        } finally {
            // Fixme Callback
            //parallelize().callbackMethodExit();
        }
    }


    public static void run(Runnable runnable, int methodId) {
        ThreadLocalForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();

        parallelize().beginThreadMethodEnter(callbackStatePerThread, new RunnableOrThreadWrapper(runnable));
        try {
            if (runnable instanceof FutureTask) {
                int atomicId = AtomicClassRepo.getId4AtomicClass("java/util/concurrent/FutureTask");
                MethodCallback.atomicMethodEnterWithCallback(atomicId, methodId);
            }
            runnable.run();
        } finally {
            if (runnable instanceof FutureTask) {
                int atomicId = AtomicClassRepo.getId4AtomicClass("java/util/concurrent/FutureTask");
                MethodCallback.atomicMethodExitWithCallback(atomicId, methodId);
            }
            // Fixme Callback
            //ParallelizeFacade.beginThreadMethodExit(callbackStatePerThread);

        }
    }


    public static Object get(Future future, int methodId) throws InterruptedException, ExecutionException {
        try {
            return future.get();
        } finally {
            if (future instanceof FutureTask) {
                ThreadLocalForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();
                // Fixme Callback
                // ParallelizeFacade.afterFutureGet(callbackStatePerThread, future);
            }
        }
    }
}
