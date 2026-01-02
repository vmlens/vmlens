package com.vmlens.test.maven.plugin;

import com.vmlens.api.AllInterleavings;
import com.vmlens.api.Interleaving;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class TestIterable {
    @Test
    void testForEachClassWithVolatileField() {
        Set<Integer> countSet = new HashSet<>();
        try (AllInterleavings allInterleavings = new AllInterleavings("testForEachClassWithVolatileField")) {
            for (Interleaving interleaving : allInterleavings) {
                countSet.add(interleaving.getIteration());
            }
            assertThat(countSet, is(Set.of(0, 1)));
        }
    }

    @Test
    void testForEachFunctionalClassWithVolatileField() {
        Set<Integer> countSet = new HashSet<>();
        try (AllInterleavings allInterleavings = new AllInterleavings("testForEachFunctionalClassWithVolatileField")) {
            allInterleavings.forEach(interleaving -> countSet.add(interleaving.getIteration()));
            assertThat(countSet, is(Set.of(0, 1)));
        }
    }
}
