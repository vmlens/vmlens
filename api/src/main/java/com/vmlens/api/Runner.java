package com.vmlens.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility to run a runnable in a separate thread. Each runnable is run in a separate thread.
 * Example:
 * <pre>{@code
@Test
public void exampleTest()  {
    try (AllInterleavings allInterleavings = new AllInterleavings("exampleRunner")) {
        while (allInterleavings.hasNext()) {
        // recreate the object at each run
        ClassUnderTest classUnderTest = new ClassUnderTest();
        // runs methodA and methodB in different threads
        runParallel(classUnderTest::methodA,
        classUnderTest::methodB);
        // check after both threads are joined
        assertThat(classUnderTest.isValid(),is(true))  ;
        }
    }
}
 * }</pre>
 *
 *
 */


public class Runner {

    private Runner() {
    }

    public static void runParallel(Runnable... tasks) {
        RuntimeExceptionContainer exceptionContainer = new RuntimeExceptionContainer();
        List<ExceptionHandlerForRunnable> withExceptionHandler = new ArrayList<>();
        for (Runnable task : tasks) {
            withExceptionHandler.add(new ExceptionHandlerForRunnable(task,exceptionContainer));
        }
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

}
