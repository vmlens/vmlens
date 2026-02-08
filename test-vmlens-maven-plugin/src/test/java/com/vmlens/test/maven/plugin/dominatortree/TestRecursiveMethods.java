package com.vmlens.test.maven.plugin.dominatortree;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestRecursiveMethods {

    final AtomicInteger atomicInteger = new AtomicInteger();

    @Test
    public void testUpdate() throws InterruptedException {
        try(AllInterleavings allInterleavings = new AllInterleavings("dominatorTreeTestRecursiveMethods")) {
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
            }
        }
    }

    private void first() {
        second(10);
    }



    private void alternativeFirst() {
        second(10);
    }

    private void second(int callCount) {
        if(callCount > 0) {
            second(callCount-1);
        }
        third();
    }

    private void third() {
        dom();
    }

    private void dom() {
        atomicInteger.incrementAndGet();
    }

}
