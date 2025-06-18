package com.vmlens.test.maven.plugin;

import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestForkJoin {

    private volatile int j = 0;

    @Test
    @Disabled
    public void testReadWrite() throws InterruptedException {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);
        Set<Integer> countSet = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("testForkJoin")) {
            while (allInterleavings.hasNext()) {
                ForkJoinPool pool = new ForkJoinPool(2);
                j = 0;
                ForkJoinTask<?> task = pool.submit(new Runnable() {
                    @Override
                    public void run() {
                        j++;
                    }
                });
                j++;
                task.join();
                countSet.add(j);
            }
            assertThat(countSet,is(expectedSet));
        }
    }


}
