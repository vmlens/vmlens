package com.vmlens.test.maven.plugin.reflection;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestAtomicLongFieldUpdater {

    public volatile long j = 0;

    @Test
    public void testReadWrite() throws InterruptedException {
        AtomicLongFieldUpdater<TestAtomicLongFieldUpdater> fieldUpdater =
                AtomicLongFieldUpdater.newUpdater(TestAtomicLongFieldUpdater.class, "j");

        Set<Long> expectedSet = new HashSet<>();
        expectedSet.add(0L);
        expectedSet.add(2L);

        Set<Long> actual = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("testAtomicLongFieldUpdater")) {
            while (allInterleavings.hasNext()) {
                j = 0;
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        fieldUpdater.set(TestAtomicLongFieldUpdater.this,2);
                    }
                };
                first.start();
                long i = fieldUpdater.get(this);
                first.join();
                actual.add(i);
            }
            assertThat(actual,is(expectedSet));
        }
    }

}
