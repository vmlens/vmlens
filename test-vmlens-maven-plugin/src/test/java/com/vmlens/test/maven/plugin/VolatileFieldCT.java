package com.vmlens.test.maven.plugin;

import com.vmlens.api.AllInterleaving;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class VolatileFieldCT {

    private volatile int j = 0;

    @Test
    public void testUpdate() throws InterruptedException {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);

        Set<Integer> countSet = new HashSet<>();
        AllInterleaving testUpdate = new AllInterleaving("testVolatileField");
        while (testUpdate.hasNext()) {
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
            assertThat(j,is(2));
            countSet.add(j);
        }
        assertThat(countSet,is(expectedSet));
    }
}
