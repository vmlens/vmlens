package com.vmlens.test.maven.plugin.barrier;

import com.vmlens.api.AllInterleavings;
import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import static com.vmlens.api.Runner.runParallel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestFuture {

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

    @Test
    public void testGetState() throws Throwable {
        Set<Boolean> expectedSet = new HashSet<>();
        expectedSet.add(true);
        expectedSet.add(false);

        Set<Boolean> actualSet = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("testFutureGetState")) {
            while (allInterleavings.hasNext()) {
                FutureTask<MutableInt> task = new FutureTask<>( () -> {
                    MutableInt mutableInt = new MutableInt();
                    mutableInt.setValue(5);
                    return mutableInt;}
                );
                runParallel(task,
                        () -> {
                            actualSet.add(task.isDone());
                        }
                );
            }
        }
        assertThat(actualSet,is(expectedSet));
    }


}
