package com.vmlens.test.maven.plugin.monitor;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

public class TestBlocking {

    @Test
    public void testBlocking() throws InterruptedException {
        try(AllInterleavings allInterleavings = new AllInterleavings("testBlocking")) {
            while (allInterleavings.hasNext()) {
                final Object monitor = new Object();
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        synchronized (monitor) {
                            try {
                                monitor.wait(500);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                };
                first.start();
                synchronized (monitor) {
                    try {
                        monitor.wait(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                first.join();
            }
        }

    }

}
