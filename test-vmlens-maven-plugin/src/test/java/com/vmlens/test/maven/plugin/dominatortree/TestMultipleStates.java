package com.vmlens.test.maven.plugin.dominatortree;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class TestMultipleStates {

    private final AtomicInteger atomicInteger = new AtomicInteger();
    private final AtomicInteger secondInteger = new AtomicInteger();
    private volatile int i = 0;

    @Test
    public void testUpdate() throws InterruptedException {
        try(AllInterleavings allInterleavings = new AllInterleavings("dominatorTreeTestMultipleStates")) {
            while (allInterleavings.hasNext()) {
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        first();
                    }
                };
                first.start();
                first();
                first.join();
            }
        }
    }
    
    public void first() {
        second();
    }
    
    public void second() {
        third();
    }

    public void third() {
       // atomicInteger.compareAndSet(0, 1);
        i++;
       // secondInteger.incrementAndGet();
    }
    
    
}
