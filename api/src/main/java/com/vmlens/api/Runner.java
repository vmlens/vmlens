package com.vmlens.api;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void runParallel(Runnable... tasks) {
        ExceptionContainer exceptionContainer = new ExceptionContainer();
        List<Thread> threads = new ArrayList<>();
        for (Runnable task : tasks) {
            Thread t = new Thread(new ExceptionHandlerForRunnable(task,exceptionContainer));
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
