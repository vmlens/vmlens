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

public class TestClinit {

    public volatile int j;

    private final static AtomicIntegerFieldUpdater<TestClinit> updater =
            AtomicIntegerFieldUpdater.newUpdater(TestClinit.class, "j");

    @Test
    public void update() {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(3);
        expectedSet.add(7);

        Set<Integer> countSet = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("testAtomicIntegerFieldUpdater.clinit")) {
            while (allInterleavings.hasNext()) {
                j = 0;

                runParallel( () -> {
                    updater.set(this,7);
                }, () -> {
                    updater.set(this,3);
                } );

                countSet.add(j);

            }
        }
        assertThat(countSet,is(expectedSet));
    }

}
