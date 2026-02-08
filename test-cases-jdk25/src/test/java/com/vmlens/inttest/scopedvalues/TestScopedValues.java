package com.vmlens.inttest.scopedvalues;

import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static com.vmlens.api.Runner.runParallel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestScopedValues {

    private static final ScopedValue<Integer> VALUE = ScopedValue.newInstance();
    private volatile int j = 0;

    @Test
    public void testScopedValues() {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);
        Set<Integer> countSet = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("scopedvalues")) {
            while (allInterleavings.hasNext()) {
                j = 0;
                runParallel(  () -> ScopedValue.where(VALUE, 1).run(() -> j = VALUE.get() ) ,
                        () -> ScopedValue.where(VALUE, 2).run(() -> j = VALUE.get() )
                );
                countSet.add(j);
            }
            assertThat(countSet,is(expectedSet));
        }
    }


}
