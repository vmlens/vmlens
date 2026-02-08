package com.vmlens.test.maven.plugin.dominatortree;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSynchronizedBlock {

    private int j = 0;

    @Test
    public void testUpdate() throws InterruptedException {
        AllInterleavings testUpdate = new AllInterleavings("dominatorTreeSynchronizedBlock");
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
