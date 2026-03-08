package com.vmlens.inttest.projects.pulsar;

import com.vmlens.api.AllInterleavings;
import com.vmlens.api.AllInterleavingsBuilder;
import org.apache.pulsar.common.util.collections.GrowableArrayBlockingQueue;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static com.vmlens.api.AllInterleavings.doNotTrace;
import static com.vmlens.api.Runner.runParallel;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class TestGrowableArrayBlockingQueue {

    @Test
    public void testWithResize()  {
        try (AllInterleavings allInterleavings = new AllInterleavingsBuilder()
                .withMaximumIterations(200)
                .build("testGrowableArrayBlockingQueueWithResize")) {
            while (allInterleavings.hasNext()) {
                List<String> readValues = new LinkedList<>();
                GrowableArrayBlockingQueue<String> growableArrayBlockingQueue = new GrowableArrayBlockingQueue<>(0);
                runParallel(() -> {
                            readValue(growableArrayBlockingQueue, readValues);
                        },
                        () -> growableArrayBlockingQueue.put("eins"),
                        () -> growableArrayBlockingQueue.put("zwei"),
                        () -> {
                            readValue(growableArrayBlockingQueue, readValues);
                        });
                String result = growableArrayBlockingQueue.poll();
                while (result != null) {
                    readValues.add(result);
                    result = growableArrayBlockingQueue.poll();
                }
                assertThat(readValues, containsInAnyOrder("eins", "zwei"));
            }
        }
    }

    private void readValue(GrowableArrayBlockingQueue<String> growableArrayBlockingQueue, List<String> readValues) {
        String result = growableArrayBlockingQueue.poll();
        if (result != null) {
            doNotTrace(() -> readValues.add(result));
        }
    }

    @Test
    public void testWithResizeTwo() throws InterruptedException {
        try (AllInterleavings allInterleavings = new AllInterleavingsBuilder()
                .withMaximumIterations(200)
                .build("testGrowableArrayBlockingQueueWithResizeTwo")) {
            while (allInterleavings.hasNext()) {
                GrowableArrayBlockingQueue<String> growableArrayBlockingQueue = new GrowableArrayBlockingQueue<>(0);
                growableArrayBlockingQueue.put("zwei");
                runParallel(
                        () -> growableArrayBlockingQueue.put("eins"),
                        growableArrayBlockingQueue::poll);
            }
        }
    }

    @Test
    public void testClear() throws InterruptedException {
        try (AllInterleavings allInterleavings = new AllInterleavingsBuilder()
                .withMaximumIterations(200)
                .build("testGrowableArrayBlockingQueueClear")) {
            while (allInterleavings.hasNext()) {
                GrowableArrayBlockingQueue<String> growableArrayBlockingQueue = new GrowableArrayBlockingQueue<>();
                growableArrayBlockingQueue.put("zwei");
                growableArrayBlockingQueue.put("zwei");
                runParallel(
                        () -> growableArrayBlockingQueue.put("eins"),
                        () -> growableArrayBlockingQueue.put("zwei"),
                        growableArrayBlockingQueue::clear
                );
            }
        }
    }
}
