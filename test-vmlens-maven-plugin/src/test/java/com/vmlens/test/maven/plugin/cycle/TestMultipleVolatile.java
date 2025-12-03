package com.vmlens.test.maven.plugin.cycle;

import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static com.vmlens.api.Runner.runParallel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestMultipleVolatile {

    private volatile int j = 0;

    @Test
    public void testMultipleVolatile() {
        Set<Integer> expectedSet = new HashSet<>();

        expectedSet.add(4);
        expectedSet.add(5);
        expectedSet.add(6);
        expectedSet.add(7);
        expectedSet.add(8);

        Set<Integer> countSet = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("testMultipleVolatile")) {
            while (allInterleavings.hasNext()) {
                j = 0;
                runParallel(  () -> { j++; j++;  j++; ;  j++; } , () -> { j++;  j++;  j++;  j++;}  );
                countSet.add(j);
            }
            assertThat(countSet,is(expectedSet));
        }
    }

    @Test
    public void testMultipleVolatileTwoThreads() throws InterruptedException {
        Set<Integer> expectedSet = new HashSet<>();
        
        expectedSet.add(2);
        expectedSet.add(3);
        expectedSet.add(4);
        expectedSet.add(5);
        expectedSet.add(6);

        Set<Integer> countSet = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("testMultipleVolatileTwoThreads")) {
            while (allInterleavings.hasNext()) {
                j = 0;
                Thread first = new Thread( () -> { j++;  j++; j++;});
                first.start();
                j++;
                j++;
                j++;
                first.join();
                countSet.add(j);
            }
            assertThat(countSet,is(expectedSet));
        }
    }

}
