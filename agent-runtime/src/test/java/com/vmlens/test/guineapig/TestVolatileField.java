package com.vmlens.test.guineapig;

import org.junit.Test;


public class TestVolatileField {
    volatile int j = 0;

    @Test
    public void testUpdate() throws InterruptedException {
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
