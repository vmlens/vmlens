package com.vmlens.test.maven.plugin.dominatortree;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestAtomicInteger {

    @Test
    public void testUpdate() throws InterruptedException {
       int count = 0;

        try(AllInterleavings allInterleavings = new AllInterleavings("dominatorTreeAtomicInteger")) {
            while (allInterleavings.hasNext()) {
                final AtomicInteger atomicInteger = new AtomicInteger();
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        dom(atomicInteger);
                    }
                };
                first.start();
                dom(atomicInteger);
                first.join();
                count++;
            }
        }
        assertThat(count,is(4));
    }

    private void dom(AtomicInteger atomicInteger) {
        atomicInteger.incrementAndGet();
    }

}
