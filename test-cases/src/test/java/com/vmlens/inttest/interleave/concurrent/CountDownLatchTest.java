package com.vmlens.inttest.interleave.concurrent;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;

import static com.vmlens.api.Runner.runParallel;

public class CountDownLatchTest {

    @Test
    public void testCountDownLatch()  {

        try(AllInterleavings allInterleavings = new AllInterleavings("testCountDownLatch")) {
            while (allInterleavings.hasNext()) {
                CountDownLatch latch = new CountDownLatch(1);
                runParallel(  () -> {
                            try {
                                latch.await();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        },
                        latch::countDown);
            }
        }
    }
}
