package com.vmlens.test.maven.plugin;

import com.vmlens.api.AllInterleaving;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class RunnableCT {

    public static volatile int STATIC_FIELD = 0;

    @Test
    public void testUpdate() throws InterruptedException {

        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);

        Set<Integer> countSet = new HashSet<>();
        AllInterleaving testUpdate = new AllInterleaving("testRunnable");
        while (testUpdate.hasNext()) {
            STATIC_FIELD = 0;
            Thread first = new Thread(new UpdateVolatileField());
            first.start();
            STATIC_FIELD++;
            first.join();
            countSet.add(STATIC_FIELD);
        }
        assertThat(countSet,is(expectedSet));
    }
}
