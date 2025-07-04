package com.vmlens.inttest.interleave;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

public class MonitorIntTest {

    private final Object MONITOR = new Object();

    @Test
    public void testUpdate() throws InterruptedException {
        AllInterleavings testUpdate = new AllInterleavings("monitorIntTest");

        while (testUpdate.hasNext()) {
            Thread first = new Thread() {
                @Override
                public void run() {
                   synchronized (MONITOR) {

                   }
                }
            };
            first.start();
            synchronized (MONITOR) {

            }
            first.join();
        }
    }

}
