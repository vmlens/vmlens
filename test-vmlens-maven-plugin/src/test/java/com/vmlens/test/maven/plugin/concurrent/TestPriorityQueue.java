package com.vmlens.test.maven.plugin.concurrent;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestPriorityQueue {

    @Test
    public void testPriorityQueue() throws InterruptedException {
        Set<String> expected = new HashSet<>();
        expected.add("first");
        expected.add("second");

        Set<String> values = new HashSet<>();

        try(AllInterleavings allInterleaving = new AllInterleavings("priorityQueue")) {
            while (allInterleaving.hasNext()) {
                PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<>();
                queue.add("second");
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        queue.add("first");
                    }
                };
                first.start();
                values.add(queue.remove());

                first.join();

            }
            assertThat(values,is(expected));
        }
    }

}
