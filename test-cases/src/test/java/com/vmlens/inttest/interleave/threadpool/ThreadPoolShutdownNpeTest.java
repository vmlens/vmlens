package com.vmlens.inttest.interleave.threadpool;

import com.vmlens.api.AllInterleavings;
import com.vmlens.api.Runner;
import org.junit.Test;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Test for GitHub issue #58: NPE when shutting down a ThreadPoolExecutor during a test.
 *
 * The issue occurs when a ThreadPoolExecutor is shut down via Runner.runParallel()
 * without any tasks having been submitted to the pool. This causes a NullPointerException
 * in ThreadPoolMap.joinAll() because poolToTaskList.get(pool) returns null.
 *
 * @see <a href="https://github.com/vmlens/vmlens/issues/58">GitHub Issue #58</a>
 */
public class ThreadPoolShutdownNpeTest {

    @Test
    public void testShutdownWithoutSubmittedTasks() throws Exception {
        try (AllInterleavings allInterleavings = new AllInterleavings("threadPoolShutdownNpe", true)) {
            while (allInterleavings.hasNext()) {
                ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(3);
                Runner.runParallel(() -> executor.shutdown());
            }
        }
    }
}
