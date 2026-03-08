package com.vmlens.test.maven.plugin.barrier;

import com.vmlens.api.AllInterleavings;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.vmlens.api.Runner.runParallel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCountDownLatch {

    private int i = 0;
    private volatile int x = 0;

    @Test
    public void testBlocking() throws Throwable {
        try(AllInterleavings allInterleavings = new AllInterleavings("testCountDownLatchBlocking")) {
            while (allInterleavings.hasNext()) {
                CountDownLatch latch = new CountDownLatch(1);
                runParallel(
                        () -> {
                            i = 9;
                            latch.countDown();
                            } ,
                        () -> {
                            try {
                                latch.await();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            int x = i;
                        }
                );
            }
        }
    }

    @Test
    public void testTimeout() throws Throwable {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(0);
        expectedSet.add(9);

        Set<Integer> actual = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("testCountDownLatchTimeout")) {
            while (allInterleavings.hasNext()) {
                CountDownLatch latch = new CountDownLatch(1);
                x = 0;
                runParallel(
                        () -> {
                            x = 9;
                            latch.countDown();
                        } ,
                        () -> {
                            try {
                                latch.await(1, TimeUnit.MILLISECONDS);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            actual.add(x);
                        }
                );
            }
            assertThat(actual,is(expectedSet));
        }
    }


}
