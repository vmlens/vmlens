package com.vmlens.test.maven.plugin;

import com.vmlens.api.AllInterleaving;
import org.junit.Test;


public class TestVolatileField {

    private volatile int j = 0;

    @Test
    public void testUpdate() throws InterruptedException {
        AllInterleaving testUpdate = new AllInterleaving("testVolatileField");

        while (testUpdate.hasNext()) {
            j = 0;
            Thread first = new Thread() {
                @Override
                public void run() {
                    j++;
                }
            };
            first.start();
            j++;
            first.join();
        }
    }
}
