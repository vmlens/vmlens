package com.vmlens.test.maven.plugin.cycle;

import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static com.vmlens.api.Runner.runParallel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestMultipleVolatile {

    private volatile int j = 0;

    @Disabled
    @Test
    public void testMultipleVolatile() {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(3);
        expectedSet.add(4);
        expectedSet.add(5);
        expectedSet.add(6);

        Set<Integer> countSet = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("testMultipleVolatile")) {
            while (allInterleavings.hasNext()) {
                j = 0;
                runParallel(  () -> { j++; j++; } , () -> { j++;  j++;}  );
                countSet.add(j);
            }
            assertThat(expectedSet,is(countSet));
        }
    }

}
