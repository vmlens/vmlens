package com.vmlens.inttest.projects.hiero;


import com.vmlens.api.AllInterleavings;
import org.junit.Test;


public class ConcurrentArrayTest {

    @Test
    public void testAdd() throws InterruptedException {
        try(AllInterleavings allInterleavings = new AllInterleavings("hiero.testAdd")) {
            while (allInterleavings.hasNext()) {
                ConcurrentArray<String> concurrentArray = new ConcurrentArray<>(1);
                concurrentArray.add("a");
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        concurrentArray.add("b");
                    }
                };
                first.start();
                concurrentArray.add("c");
                first.join();
            }
        }
    }

}