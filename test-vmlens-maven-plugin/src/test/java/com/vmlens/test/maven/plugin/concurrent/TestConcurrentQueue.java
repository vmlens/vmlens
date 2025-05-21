package com.vmlens.test.maven.plugin.concurrent;

import com.vmlens.api.AllInterleavings;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.Test;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestConcurrentQueue {

    @Test
    public void testLinkedBlockingQueue() throws InterruptedException {
        runAllTest(LinkedBlockingQueue::new,"linkedBlockingQueue");
    }

    @Test
    public void testArrayBlockingQueue() throws InterruptedException {
        runAllTest(() -> new ArrayBlockingQueue<>(5),"arrayBlockingQueue");
    }

    @Test
    public void testConcurrentLinkedQueue() throws InterruptedException {
        runAllTest(ConcurrentLinkedQueue::new,"concurrentLinkedQueue");
    }

    private void runAllTest(Supplier<Queue<String>> createQueue, String name) throws InterruptedException {
        runCountTest(createQueue, name + "Count");
        runOrderTest(createQueue, name + "Order");
    }

    private void runCountTest(Supplier<Queue<String>> createQueue, String name) throws InterruptedException {
        Set<Integer> expected = new HashSet<>();
        expected.add(1);
        expected.add(2);

        Set<Integer> values = new HashSet<>();

        try(AllInterleavings allInterleavings = new AllInterleavings(name)) {
            while (allInterleavings.hasNext()) {
                Queue<String> queue = createQueue.get();
                queue.add("first");
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        queue.add("first");
                    }
                };
                first.start();
                values.add(queue.size());

                first.join();

            }
            assertThat(values,is(expected));
        }
    }

    private void runOrderTest(Supplier<Queue<String>> createQueue, String name) throws InterruptedException {
        Set<ImmutablePair<String,String>> expected = new HashSet<>();
        expected.add(ImmutablePair.of("first", "second"));
        expected.add(ImmutablePair.of("second","first"));

        Set<ImmutablePair<String,String>> actual = new HashSet<>();

        try(AllInterleavings allInterleavings = new AllInterleavings(name)) {
            while (allInterleavings.hasNext()) {
                Queue<String> queue = createQueue.get();

                Thread first = new Thread() {
                    @Override
                    public void run() {
                        queue.add("first");
                    }
                };
                first.start();
                queue.add("second");
                first.join();
                actual.add(ImmutablePair.of(queue.poll(), queue.poll()));
            }
            assertThat(actual,is(expected));
        }
    }

}
