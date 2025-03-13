package com.vmlens.test.maven.plugin;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

public class TestDeadlock {


    private final Object A = new Object();
    private final Object B = new Object();

    @Test
    public void testUpdate() throws InterruptedException {
        AllInterleavings testUpdate = new AllInterleavings("testDeadlock");
        while (testUpdate.hasNext()) {

            Thread first = new Thread() {
                @Override
                public void run() {
                    bToA();
                }
            };
            first.start();
            aToB();
            first.join();

        }
    }

    private void aToB() {
        synchronized (A) {
            synchronized (B) {

            }
        }

    }

    private void bToA() {
        synchronized (B) {
            synchronized (A) {

            }
        }

    }

}
