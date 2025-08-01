package com.vmlens.inttest.interleave.threadpool;

import com.vmlens.api.AllInterleavings;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolInsideTest {

    private volatile int j = 0;

    public class UpdateField implements Runnable {

        @Override
        public void run() {
            j = 0;
        }
    }

    @Test
    public void testUpdate() throws InterruptedException {
        AllInterleavings testUpdate = new AllInterleavings("threadPoolInsideTest");
        while (testUpdate.hasNext()) {
            ExecutorService pool = Executors.newFixedThreadPool(1);
            pool.submit(new UpdateField());
            int i = j;
            pool.shutdown();
        }
    }

}
