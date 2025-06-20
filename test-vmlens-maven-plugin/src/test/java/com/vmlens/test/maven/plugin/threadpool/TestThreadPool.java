package com.vmlens.test.maven.plugin.threadpool;

import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestThreadPool {

    private volatile int j = 0;

    @Test
    public void testReadWrite() throws InterruptedException {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);
        Set<Integer> countSet = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("testThreadPool")) {
            while (allInterleavings.hasNext()) {
                ExecutorService executor = Executors.newFixedThreadPool(4);
                j = 0;
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        j++;
                    }
                });
                j++;
                executor.shutdown();
                countSet.add(j);
            }
            assertThat(countSet,is(expectedSet));
        }
    }

    @Test
    public void testShutdownMissing() throws InterruptedException {

        try(AllInterleavings allInterleavings = new AllInterleavings("testShutdownMissing")) {
            while (allInterleavings.hasNext()) {
                ExecutorService executor = Executors.newFixedThreadPool(4);
                j = 0;
                executor.submit(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }

        }
    }

}