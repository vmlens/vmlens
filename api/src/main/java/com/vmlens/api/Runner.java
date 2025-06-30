package com.vmlens.api;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void runParallel(Runnable... tasks) {
        RuntimeExceptionContainer exceptionContainer = new RuntimeExceptionContainer();
        List<ExceptionHandlerForRunnable> withExceptionHandler = new ArrayList<>();
        for (Runnable task : tasks) {
            withExceptionHandler.add(new ExceptionHandlerForRunnable(task,exceptionContainer));
        }
        runParallel(exceptionContainer,withExceptionHandler);
    }

    public static void runParallelWithException(RunnableWithException... tasks) throws Throwable {
        ThrowableExceptionContainer exceptionContainer = new ThrowableExceptionContainer();
        List<ExceptionHandlerForRunnableWithException> withExceptionHandler = new ArrayList<>();
        for (RunnableWithException task : tasks) {
            withExceptionHandler.add(new ExceptionHandlerForRunnableWithException(task,exceptionContainer));
        }
        runParallelWithException(exceptionContainer,withExceptionHandler);
    }

    public static void runTwiceParallel(Runnable task) {
        RuntimeExceptionContainer exceptionContainer = new RuntimeExceptionContainer();
        List<ExceptionHandlerForRunnable> withExceptionHandler = new ArrayList<>();
        withExceptionHandler.add(new ExceptionHandlerForRunnable(task,exceptionContainer));
        withExceptionHandler.add(new ExceptionHandlerForRunnable(task,exceptionContainer));
        runParallel(exceptionContainer,withExceptionHandler);
    }

    private static void runParallel(RuntimeExceptionContainer exceptionContainer,
                                    List<ExceptionHandlerForRunnable> tasks) {
        List<Thread> threads = new ArrayList<>();
        for (ExceptionHandlerForRunnable task : tasks) {
            Thread t = new Thread(task);
            threads.add(t);
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if(exceptionContainer.exception() != null) {
            throw exceptionContainer.exception();
        }
    }

    private static void runParallelWithException(ThrowableExceptionContainer exceptionContainer,
                                    List<ExceptionHandlerForRunnableWithException> tasks) throws Throwable {
        List<Thread> threads = new ArrayList<>();
        for (ExceptionHandlerForRunnableWithException task : tasks) {
            Thread t = new Thread(task);
            threads.add(t);
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if(exceptionContainer.exception() != null) {
            throw exceptionContainer.exception();
        }
    }

}
