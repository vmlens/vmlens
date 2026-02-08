package com.vmlens.test.maven.plugin.dominatortree;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSameMethods {

    final AtomicInteger atomicInteger = new AtomicInteger();

    @Test
    public void testUpdate() throws InterruptedException {
        int count = 0;

        try(AllInterleavings allInterleavings = new AllInterleavings("dominatorTreeTestSameMethods")) {
            while (allInterleavings.hasNext()) {
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        first();
                    }
                };
                first.start();
                alternativeFirst();
                first.join();
                count++;
            }
        }
        assertThat(count,is(4));
    }

    private void first() {
        second();
    }



    private void alternativeFirst() {
        second();
    }

    private void second() {
        third();
    }

    private void third() {
        dom();
    }

    private void dom() {
        atomicInteger.incrementAndGet();
    }

}
