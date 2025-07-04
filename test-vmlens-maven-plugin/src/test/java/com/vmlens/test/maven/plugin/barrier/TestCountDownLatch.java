package com.vmlens.test.maven.plugin.barrier;

import com.vmlens.api.AllInterleavings;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.vmlens.api.Runner.runParallelWithException;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCountDownLatch {

    private int i = 0;
    private volatile int x = 0;

    @Test
    @Ignore
    public void testBlocking() throws Throwable {
        try(AllInterleavings allInterleavings = new AllInterleavings("testCountDownLatchBlocking")) {
            while (allInterleavings.hasNext()) {
                CountDownLatch latch = new CountDownLatch(1);
                runParallelWithException(
                        () -> {
                            i = 9;
                            latch.countDown();
                            } ,
                        () -> {
                            latch.await();
                            int x = i;
                        }
                );
            }
        }
    }

    @Test
    @Ignore
    public void testTimeout() throws Throwable {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(0);
        expectedSet.add(9);

        Set<Integer> actual = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("testCountDownLatchTimeout")) {
            while (allInterleavings.hasNext()) {
                CountDownLatch latch = new CountDownLatch(1);
                x = 0;
                runParallelWithException(
                        () -> {
                            x = 9;
                            latch.countDown();
                        } ,
                        () -> {
                            latch.await(1, TimeUnit.MILLISECONDS);
                            actual.add(x);
                        }
                );
            }
            assertThat(actual,is(expectedSet));
        }
    }


}
