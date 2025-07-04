package com.vmlens.test.maven.plugin.barrier;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.concurrent.CyclicBarrier;

import static com.vmlens.api.Runner.runParallelWithException;

public class TestCyclicBarrier {

    private int i = 0;

    @Test
    public void testBlocking() throws Throwable {
        try(AllInterleavings allInterleavings = new AllInterleavings("testCyclicBarrierBlocking")) {
            while (allInterleavings.hasNext()) {
                CyclicBarrier barrier = new CyclicBarrier(2);
                runParallelWithException(
                        () -> {
                            i = 9;
                            barrier.await();
                        } ,
                        () -> {
                            barrier.await();
                            int x = i;
                        }
                );
            }
        }
    }

}
