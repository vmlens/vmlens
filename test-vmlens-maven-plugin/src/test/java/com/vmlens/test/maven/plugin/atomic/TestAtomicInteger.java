package com.vmlens.test.maven.plugin.atomic;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestAtomicInteger {

    @Test
    public void testUpdate() throws InterruptedException {
       int count = 0;

        try(AllInterleavings allInterleavings = new AllInterleavings("testAtomicInteger")) {
            while (allInterleavings.hasNext()) {
                final AtomicInteger atomicInteger = new AtomicInteger();
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        atomicInteger.incrementAndGet();
                    }
                };
                first.start();
                atomicInteger.incrementAndGet();
                first.join();
                count++;
            }
        }
        assertThat(count,is(4));
    }

}
