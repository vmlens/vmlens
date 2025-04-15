package com.vmlens.test.maven.plugin;

import com.vmlens.api.AllInterleaving;
import org.junit.Ignore;
import org.junit.Test;

public class TestDeadlock {


    private final Object A = new Object();
    private final Object B = new Object();

    @Ignore
    @Test
    public void testUpdate() throws InterruptedException {
        AllInterleaving testUpdate = new AllInterleaving("testDeadlock");
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
