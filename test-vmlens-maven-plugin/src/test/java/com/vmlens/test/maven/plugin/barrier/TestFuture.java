package com.vmlens.test.maven.plugin.barrier;

import com.vmlens.api.AllInterleavings;
import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import static com.vmlens.api.Runner.runParallel;

public class TestFuture {

    @Disabled
    @Test
    public void testGetSet() throws Throwable {
        try(AllInterleavings allInterleavings = new AllInterleavings("testFutureBlocking")) {
            while (allInterleavings.hasNext()) {
                FutureTask<MutableInt> task = new FutureTask<>( () -> {
                    MutableInt mutableInt = new MutableInt();
                    mutableInt.setValue(5);
                    return mutableInt;}
                );
                runParallel(task,
                        () -> {
                            try {
                                MutableInt mutableInt = task.get();
                                int x = mutableInt.getValue();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            } catch (ExecutionException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
            }
        }
    }

    @Test
    public void testSubmit() throws Throwable {
        try(AllInterleavings allInterleavings = new AllInterleavings("testFutureSubmit")) {
            while (allInterleavings.hasNext()) {
                FutureTask<MutableInt> task = new FutureTask<>( () -> {
                    MutableInt mutableInt = new MutableInt();
                    mutableInt.setValue(5);
                    return mutableInt;}
                );
                ExecutorService executor = Executors.newFixedThreadPool(4);
                executor.submit(task);
                MutableInt mutableInt = task.get();
                int x = mutableInt.getValue();
                executor.shutdown();
            }
        }
    }

}
