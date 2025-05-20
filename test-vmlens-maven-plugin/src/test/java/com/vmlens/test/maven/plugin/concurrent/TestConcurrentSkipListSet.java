package com.vmlens.test.maven.plugin.concurrent;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestConcurrentSkipListSet {

    @Test
    public void test() throws InterruptedException {
        Set<Boolean> expectedSet = new HashSet<>();
        expectedSet.add(true);
        expectedSet.add(false);

        Set<Boolean> actual = new HashSet<>();
        try(AllInterleavings allInterleaving = new AllInterleavings("concurrentSkipListSet")) {
            while (allInterleaving.hasNext()) {
                final ConcurrentSkipListSet<String>  set = new ConcurrentSkipListSet<>();
                Thread first = new Thread() {
                    @Override
                    public void run() {
                       set.add("value");
                    }
                };
                first.start();
                actual.add(set.contains("value"));
                first.join();
            }
            assertThat(actual,is(expectedSet));
        }
    }
    
    
}
