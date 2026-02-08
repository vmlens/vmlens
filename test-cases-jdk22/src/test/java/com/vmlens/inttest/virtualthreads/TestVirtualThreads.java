package com.vmlens.inttest.virtualthreads;

import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestVirtualThreads {

    private volatile int j = 0;

    @Disabled
    @Test
    public void testWrite() throws InterruptedException {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(5);
        expectedSet.add(9);
        Set<Integer> countSet = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("testVirtualThreads")) {
            while (allInterleavings.hasNext()) {
                Thread.Builder builder = Thread.ofVirtual().name("worker-", 0);

                j = 0;
                Thread first = builder.unstarted(() -> j = 5);
                first.start();
                j = 9;
                first.join();
                countSet.add(j);
            }
            assertThat(countSet,is(expectedSet));
        }
    }


}
