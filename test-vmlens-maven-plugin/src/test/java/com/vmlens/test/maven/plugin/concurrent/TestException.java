package com.vmlens.test.maven.plugin.concurrent;


import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;


import static com.vmlens.api.Runner.runParallel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * number should be 4 when we filter insider pre analyzed
 */
public class TestException {

    /**
     * Tests the handling of exceptions with pre analyzed classes
     */
    @Test
    public void testWithException() {
        int count = 0;
        try (AllInterleavings allInterleavings = new AllInterleavings("testWithException")) {
            while (allInterleavings.hasNext()) {
                ConcurrentLinkedDeque<String> queue = new ConcurrentLinkedDeque<>();
                runParallel(() -> {
                    try {
                        queue.pop();
                    } catch (RuntimeException exp) {
                    }
                }, () -> queue.push("test"));
                count++;
            }
        }
        // does also require filter of internal calls not
        // only final block
        //assertThat(count, is(8));
    }

    @Test
    public void testWithoutException() {
        int count = 0;
        try (AllInterleavings allInterleavings = new AllInterleavings("testWithoutException")) {
            while (allInterleavings.hasNext()) {
                ConcurrentLinkedDeque<String> queue = new ConcurrentLinkedDeque<>();
                runParallel(queue::poll, () -> queue.push("test"));
                count++;
            }
        }
        assertThat(count, is(8));
    }


}
