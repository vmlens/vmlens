package com.vmlens.test.volatileFields.fixed;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

/**
 * -javaagent:C:\workspace\vmlens\test-vmlens-maven-plugin\target\vmlens-agent\agent.jar
 *
 *
 */

public class TestVolatileField {
    volatile int j = 0;

    @Test
    public void testUpdate() throws InterruptedException {
        AllInterleavings testUpdate = new AllInterleavings("testVolatileField");

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
