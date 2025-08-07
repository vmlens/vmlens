package com.vmlens.test.maven.plugin;

import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static com.vmlens.api.Runner.runParallel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestThreeThreads {

    private volatile int j = 0;

    /**
     *  currently stop at 500 runs
     *
     *  5   120                 6,5 s
     *  6   722                  27m
     *
     * ,() -> j = 4, () -> j = 5, () -> j = 6
     *
     * @throws InterruptedException
     */

    @Test
    public void testReadWrite() throws InterruptedException {
        try(AllInterleavings allInterleavings = new AllInterleavings("testThreeThreads")) {
            while (allInterleavings.hasNext()) {
                j = 0;
                runParallel(  () -> j = 1, () -> j = 2, () -> j = 3);
            }
        }
    }

}
