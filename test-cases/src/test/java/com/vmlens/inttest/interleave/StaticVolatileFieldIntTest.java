package com.vmlens.inttest.interleave;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

public class StaticVolatileFieldIntTest {

    private static volatile int j = 0;

    @Test
    public void testUpdate() throws InterruptedException {
        AllInterleavings testUpdate = new AllInterleavings("staticVolatileFieldIntTest");

        while (testUpdate.hasNext()) {
            Thread first = new Thread() {
                @Override
                public void run() {
                    j = 0;
                }
            };
            first.start();
            int i = j;
            first.join();
        }
    }

}
