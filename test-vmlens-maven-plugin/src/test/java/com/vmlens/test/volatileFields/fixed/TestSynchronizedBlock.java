package com.vmlens.test.volatileFields.fixed;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

public class TestSynchronizedBlock {

    private int j = 0;

    @Test
    public void testUpdate() throws InterruptedException {
        AllInterleavings testUpdate = new AllInterleavings("testSynchronizedBlock");

        while (testUpdate.hasNext()) {
            j = 0;
            Thread first = new Thread() {
                @Override
                public void run() {
                    increment();
                }
            };
            first.start();
            increment();
            first.join();
            j = 0;
        }
    }

    private void increment() {
        synchronized (this) {
            j++;
        }

    }


}
