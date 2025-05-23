package com.vmlens.test.maven.plugin;

import com.vmlens.api.AllInterleavings;
import org.junit.Ignore;
import org.junit.Test;

public class TestNonVolatileField {
    private int j = 0;
    @Ignore
    @Test
    public void testUpdate() throws InterruptedException {
        try(AllInterleavings allInterleaving = new AllInterleavings("testNonVolatileField")) {
            while (allInterleaving.hasNext()) {
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
}
