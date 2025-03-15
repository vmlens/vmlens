package com.vmlens.test.guineapig;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    public void update() throws InterruptedException {
       new AtomicInteger().getAndAccumulate(6, null);
    }

}
