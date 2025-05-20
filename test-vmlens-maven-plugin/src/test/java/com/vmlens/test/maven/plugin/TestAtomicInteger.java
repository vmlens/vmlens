package com.vmlens.test.maven.plugin;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestAtomicInteger {

    @Test
    public void testUpdate() throws InterruptedException {
       int count = 0;

        try(AllInterleavings allInterleaving = new AllInterleavings("testAtomicInteger")) {
            while (allInterleaving.hasNext()) {
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
        assertThat(count,is(3));
    }

}
