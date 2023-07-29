package com.vmlens.test.volatileFields.fixed;

import com.vmlens.api.AllInterleavings;


public class TestVolatileField {
    volatile int j = 0;

    public void testUpdate() throws InterruptedException {
        AllInterleavings testUpdate = new AllInterleavings("testVolatileField");

        while (testUpdate.hasNext()) {
            j = 0;

            Thread first = new Thread() {
                @Override
                public void run() {
                    j++;
                }
            };
            first.start();

            j++;

            first.join();
        }
    }
}
