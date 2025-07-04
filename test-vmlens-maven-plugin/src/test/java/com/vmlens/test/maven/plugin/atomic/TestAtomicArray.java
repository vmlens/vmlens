package com.vmlens.test.maven.plugin.atomic;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerArray;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestAtomicArray {

    private int j = 0;

    @Test
    public void testUpdate() throws InterruptedException {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);

        Set<Integer> countSet = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("testAtomicArray")) {
            while (allInterleavings.hasNext()) {
                final AtomicIntegerArray atomicInteger = new AtomicIntegerArray(8);
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        atomicInteger.incrementAndGet(3);
                    }
                };
                first.start();
                countSet.add(atomicInteger.incrementAndGet(3));
                first.join();
            }
        }
        assertThat(countSet,is(expectedSet));
    }

    @Test
    public void testHappensBefore() throws InterruptedException {
        try(AllInterleavings allInterleavings = new AllInterleavings("testHappensBefore")) {
            while (allInterleavings.hasNext()) {
                final AtomicIntegerArray atomicInteger = new AtomicIntegerArray(8);
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        j = 9;
                        atomicInteger.set(5, 5);
                    }
                };
                first.start();
                int value = atomicInteger.get(5);
                if(value == 5) {
                    value = j;
                }
                first.join();
            }
        }
    }
}
