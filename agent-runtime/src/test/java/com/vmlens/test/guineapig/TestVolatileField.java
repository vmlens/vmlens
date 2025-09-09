package com.vmlens.test.guineapig;

import org.junit.Test;


public class TestVolatileField {
    volatile int j = 0;

    @Test
    public void update() throws InterruptedException {
        j = 0;

        j++;

    }
}
