package com.vmlens.inttest.projects.pulsar;

import com.vmlens.api.AllInterleavings;
import org.apache.pulsar.common.util.collections.GrowableArrayBlockingQueue;
import org.junit.Test;

import static com.vmlens.api.Runner.runParallel;

public class TestGrowableArrayBlockingQueue {

    @Test
    public void testPollAndPeak() throws InterruptedException {

        try(AllInterleavings allInterleavings = new AllInterleavings("testGrowableArrayBlockingQueue")) {
            while (allInterleavings.hasNext()) {
                GrowableArrayBlockingQueue<String> growableArrayBlockingQueue = new GrowableArrayBlockingQueue<>();

                runParallel(growableArrayBlockingQueue::peek,
                        () -> growableArrayBlockingQueue.put("eins"),
                        growableArrayBlockingQueue::poll);
            }
        }
    }


}
