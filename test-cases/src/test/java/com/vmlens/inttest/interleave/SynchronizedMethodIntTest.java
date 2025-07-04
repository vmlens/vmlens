package com.vmlens.inttest.interleave;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

public class SynchronizedMethodIntTest {

    @Test
    public void testUpdate() throws InterruptedException {
        AllInterleavings testUpdate = new AllInterleavings("synchronizedMethodIntTest");

        while (testUpdate.hasNext()) {
            Thread first = new Thread() {
                @Override
                public void run() {
                    synchronizedMethod();
                }
            };
            first.start();
            synchronizedMethod();
            first.join();
        }
    }

    private synchronized void synchronizedMethod() {

    }
}
