package com.vmlens.test.maven.plugin.reflection;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestAtomicIntegerFieldUpdater {

    public volatile int j = 0;

    @Test
    public void testReadWrite() throws InterruptedException {
        AtomicIntegerFieldUpdater<TestAtomicIntegerFieldUpdater> fieldUpdater =
                AtomicIntegerFieldUpdater.newUpdater(TestAtomicIntegerFieldUpdater.class, "j");

        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(0);
        expectedSet.add(2);

        Set<Integer> actual = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("testAtomicIntegerFieldUpdater")) {
            while (allInterleavings.hasNext()) {
                j = 0;
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        fieldUpdater.set(TestAtomicIntegerFieldUpdater.this,2);
                    }
                };
                first.start();
                int i = fieldUpdater.get(this);
                first.join();
                actual.add(i);
            }
            assertThat(actual,is(expectedSet));
        }
    }

}
