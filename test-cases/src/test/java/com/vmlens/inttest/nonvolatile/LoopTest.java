package com.vmlens.inttest.nonvolatile;

import com.vmlens.api.AllInterleavings;
import com.vmlens.api.AllInterleavingsBuilder;
import org.junit.Test;

public class LoopTest {

    private boolean stop;
    private volatile int i = 0;

    /**
     * shows that we also need to check for loops for non interleave actions.
     *
     */
    @Test
    public void whileLoop() throws InterruptedException {
        AllInterleavings testUpdate = new  AllInterleavingsBuilder()
                .withMaximumIterations(3)
                .build("whileLoop");
        while (testUpdate.hasNext()) {
            stop = false;
            Thread first = new Thread() {
                @Override
                public void run() {
                    i++;
                    stop=true;
                    i++;
                }
            };
            first.start();
            i++;
            while(! stop) {
            }
            i++;
            first.join();
        }
    }

}
