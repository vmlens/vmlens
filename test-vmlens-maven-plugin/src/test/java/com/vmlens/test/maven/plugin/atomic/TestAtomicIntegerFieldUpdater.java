package com.vmlens.test.maven.plugin.atomic;

import com.vmlens.api.AllInterleavings;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import static com.vmlens.api.Runner.runParallel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestAtomicIntegerFieldUpdater {

    public volatile int j;

    @Test
    @Disabled
    public void update() {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(3);
        expectedSet.add(7);

        Set<Integer> countSet = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("testAtomicIntegerFieldUpdater")) {
            while (allInterleavings.hasNext()) {
                j = 0;
                AtomicIntegerFieldUpdater<TestAtomicIntegerFieldUpdater> updater =
                        AtomicIntegerFieldUpdater.newUpdater(TestAtomicIntegerFieldUpdater.class, "j");

                runParallel( () -> {
                    updater.set(this,7);
                }, () -> {
                    j = 3;
                } );

                countSet.add(j);

            }
        }
        assertThat(countSet,is(expectedSet));
    }


}
