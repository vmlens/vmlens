package com.vmlens.test.maven.plugin;

import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Test;

import static com.vmlens.api.AllInterleavings.doNotTrace;
import static com.vmlens.api.Runner.runParallel;

public class TestDoNotTrace {

    private  int j = 0;

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
        try(AllInterleavings allInterleavings = new AllInterleavings("testDoNotTrace")) {
            while (allInterleavings.hasNext()) {
                j = 0;
                runParallel(this::update ,this::update);
            }
        }
    }

    private void update() {
        doNotTrace( () -> j = 1 );
    }

}
