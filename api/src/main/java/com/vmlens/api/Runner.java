package com.vmlens.api;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void runParallel(Runnable... tasks) {
        ExceptionContainer exceptionContainer = new ExceptionContainer();
        List<ExceptionHandlerForRunnable> withExceptionHandler = new ArrayList<>();
        for (Runnable task : tasks) {
            withExceptionHandler.add(new ExceptionHandlerForRunnable(task,exceptionContainer));
        }
        runParallel(exceptionContainer,withExceptionHandler);
    }

    public static void runTwiceParallel(Runnable task) {
        ExceptionContainer exceptionContainer = new ExceptionContainer();
        List<ExceptionHandlerForRunnable> withExceptionHandler = new ArrayList<>();
        withExceptionHandler.add(new ExceptionHandlerForRunnable(task,exceptionContainer));
        withExceptionHandler.add(new ExceptionHandlerForRunnable(task,exceptionContainer));
        runParallel(exceptionContainer,withExceptionHandler);
    }

    private static void runParallel(ExceptionContainer exceptionContainer,
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

}
