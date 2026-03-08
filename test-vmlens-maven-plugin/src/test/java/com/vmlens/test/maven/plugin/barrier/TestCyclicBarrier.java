package com.vmlens.test.maven.plugin.barrier;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static com.vmlens.api.Runner.runParallel;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;


public class TestCyclicBarrier {

    private int i = 0;

    @Test
    public void testBlocking() throws Throwable {
        int count = 0;
        try(AllInterleavings allInterleavings = new AllInterleavings("testCyclicBarrierBlocking")) {
            while (allInterleavings.hasNext()) {
                CyclicBarrier barrier = new CyclicBarrier(2);
                runParallel(
                        () -> {
                            i = 9;
                            try {
                                barrier.await();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            } catch (BrokenBarrierException e) {
                                throw new RuntimeException(e);
                            }
                        } ,
                        () -> {
                            try {
                                barrier.await();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            } catch (BrokenBarrierException e) {
                                throw new RuntimeException(e);
                            }
                            int x = i;
                        }
                );
                count++;
            }
        }
        assertThat(count,greaterThan(3));
    }

}
