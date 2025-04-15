package com.vmlens.test.maven.plugin;

import com.vmlens.api.AllInterleaving;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * -javaagent:C:\workspace\vmlens\test-vmlens-maven-plugin\target\vmlens-agent\agent.jar
 *
 *
 */

public class TestSynchronizedBlock {

    private volatile int j = 0;

    @Test
    public void testUpdate() throws InterruptedException {


        AllInterleaving testUpdate = new AllInterleaving("testSynchronizedBlock");
        while (testUpdate.hasNext()) {
            j = 0;
            Thread first = new Thread() {
                @Override
                public void run() {
                    increment();
                }
            };
            first.start();
            increment();
            first.join();
            assertThat(j,is(2));
        }
    }

    private void increment() {
        synchronized (this) {
            j++;
        }

    }


}
