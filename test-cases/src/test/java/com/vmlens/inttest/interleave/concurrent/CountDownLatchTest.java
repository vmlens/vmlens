package com.vmlens.inttest.interleave.concurrent;

import org.junit.Test;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    @Test
    public void testUpdate() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        new ConcurrentSkipListSet();
    }
}
