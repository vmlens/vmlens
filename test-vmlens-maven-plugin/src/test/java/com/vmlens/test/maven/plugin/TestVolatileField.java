package com.vmlens.test.maven.plugin;

import com.vmlens.api.AllInterleavings;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static com.vmlens.api.Runner.runParallel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestVolatileField {

    private volatile int j = 0;

    @Test
    public void testReadWrite() throws InterruptedException {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);
        Set<Integer> countSet = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("testVolatileFieldReadWrite")) {
            while (allInterleavings.hasNext()) {
                j = 0;
                runParallel(  () -> { j++;} , () -> { j++;}  );
                countSet.add(j);
            }
            assertThat(countSet,is(expectedSet));
        }
    }

    @Test
    public void testWrite() throws InterruptedException {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(5);
        expectedSet.add(9);
        Set<Integer> countSet = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("testVolatileFieldWrite")) {
            while (allInterleavings.hasNext()) {
                j = 0;
                Thread first = new Thread(() -> j = 5);
                first.start();
                j = 9;
                first.join();
                countSet.add(j);
            }
            assertThat(countSet,is(expectedSet));
        }
    }
}
