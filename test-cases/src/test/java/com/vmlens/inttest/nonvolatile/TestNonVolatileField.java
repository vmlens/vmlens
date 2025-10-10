package com.vmlens.inttest.nonvolatile;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

public class TestNonVolatileField {
    private int j = 0;
    @Test
    public void testUpdate() throws InterruptedException {
        try(AllInterleavings allInterleavings = new AllInterleavings("testNonVolatileField")) {
            while (allInterleavings.hasNext()) {
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
}
