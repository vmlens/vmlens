package com.vmlens.test.maven.plugin.loop;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

public class TestStopThread {

    private volatile boolean stop = false;

    @Test
    public void testUpdate() throws InterruptedException {
        try(AllInterleavings allInterleavins = new AllInterleavings("testStopThread")) {
            while (allInterleavins.hasNext()) {
                stop = false;
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        while(!stop) {
                            Thread.yield();
                        }
                    }
                };
                first.start();
                stop = true;
                first.join();
            }
        }
    }

}
