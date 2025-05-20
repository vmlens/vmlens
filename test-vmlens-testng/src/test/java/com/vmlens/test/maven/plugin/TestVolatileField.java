package com.vmlens.test.maven.plugin;

import com.vmlens.api.AllInterleavings;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestVolatileField {

    private volatile int j = 0;

    /*
     *
     * also shows that the filter in the class loader works with exceptions, currently
     * solved through the call in VmlensApiCallback resetProcessCount()
     *
     * SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
     *
     */

    @Test
    public void testUpdate() throws InterruptedException {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);
        Set<Integer> countSet = new HashSet<>();
        try(AllInterleavings allInterleaving = new AllInterleavings("testVolatileField")) {
            while (allInterleaving.hasNext()) {
                j = 0;
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        j++;
                    }
                };
                first.start();
                j++;
                first.join();
                countSet.add(j);
            }
            assertThat(countSet,is(expectedSet));
        }
    }
}
