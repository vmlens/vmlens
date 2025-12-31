package com.vmlens.test.maven.plugin.dominatortree;

import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static com.vmlens.api.Runner.runParallel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestVolatileField {

    private volatile int j = 0;

    @Test
    public void testReadWrite() {
        try(AllInterleavings allInterleavings = new AllInterleavings("dominatorTreeVolatileFieldReadWrite")) {
            while (allInterleavings.hasNext()) {
                j = 0;
                runParallel(this::first, this::first);
            }
        }
    }

    private void first() {
        read();
        write();
    }

    private void read() {
        int i = j;
    }

    private void write() {
      j = 7;
    }

    @Test
    public void testWrite() throws InterruptedException {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(5);
        expectedSet.add(9);
        Set<Integer> countSet = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("dominatorTreeRoot")) {
            while (allInterleavings.hasNext()) {
                j = 0;
                Thread first = new Thread(() -> j = 5);
                first.start();
                j = 9;
                first.join();
                countSet.add(j);
            }
            assertThat(countSet,is(expectedSet));
        }
    }

}
