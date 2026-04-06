package com.vmlens.test.maven.plugin.barrier;

import com.vmlens.api.AllInterleavings;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static com.vmlens.api.Runner.runParallel;

public class TestCyclicBarrierAsField {

    private final CyclicBarrier barrier = new CyclicBarrier(2);

    @Test
    public void testBlocking() throws Throwable {
        try(AllInterleavings allInterleavings = new AllInterleavings("testCyclicBarrierAsField")) {
            while (allInterleavings.hasNext()) {
                runParallel(
                        () -> {
                            try {
                                barrier.await();
                            } catch (InterruptedException | BrokenBarrierException e) {
                                throw new RuntimeException(e);
                            }
                        } ,
                        () -> {
                            try {
                                barrier.await();
                            } catch (InterruptedException | BrokenBarrierException e) {
                                throw new RuntimeException(e);
                            }
                        } ,
                        () -> {
                            barrier.getNumberWaiting();
                        }
                );
                barrier.reset();
            }
        }
    }


}
