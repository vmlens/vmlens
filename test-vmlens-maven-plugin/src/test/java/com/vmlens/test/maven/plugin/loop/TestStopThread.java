package com.vmlens.test.maven.plugin.loop;

import com.vmlens.api.AllInterleavings;
import org.junit.Ignore;
import org.junit.Test;

public class TestStopThread {

    private volatile boolean stop = false;

    @Ignore
    @Test
    public void testLoopInWorkerThread() throws InterruptedException {
        try(AllInterleavings allInterleavings = new AllInterleavings("testLoopInWorkerThread")) {
            while (allInterleavings.hasNext()) {
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

    @Ignore
    @Test
    public void testLoopInMainThread() throws InterruptedException {
        try(AllInterleavings allInterleavings = new AllInterleavings("testLoopInMainThread")) {
            while (allInterleavings.hasNext()) {
                stop = false;
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        stop = true;
                    }
                };
                first.start();
                while(!stop) {

                }
                first.join();
            }
        }
    }

}
