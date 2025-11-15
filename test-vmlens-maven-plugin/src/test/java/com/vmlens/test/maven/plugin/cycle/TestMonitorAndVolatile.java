package com.vmlens.test.maven.plugin.cycle;

import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Test;

import static com.vmlens.api.Runner.runParallel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestMonitorAndVolatile {

    private final Object LOCK = new Object();
    private volatile int j = 0;

    @Test
    public void testMonitorAndVolatile() {
        try (AllInterleavings allInterleavings = new AllInterleavings("testMonitorAndVolatile")) {
            while (allInterleavings.hasNext()) {
                j = 0;
                runParallel(() -> {
                            synchronized (LOCK) {
                                j++;
                                j++;
                                j++;
                            }
                        },
                        () -> {
                            synchronized (LOCK) {
                                j++;
                                j++;
                                j++;
                            }
                        }
                );
                assertThat(j, is(6));
            }

        }
    }

}
