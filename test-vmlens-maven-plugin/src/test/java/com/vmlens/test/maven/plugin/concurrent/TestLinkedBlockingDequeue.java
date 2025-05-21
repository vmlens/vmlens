package com.vmlens.test.maven.plugin.concurrent;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Supplier;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestLinkedBlockingDequeue {

    @Test
    public void concurrentLinkedDeque() throws InterruptedException {
        testStack(ConcurrentLinkedDeque::new, "concurrentLinkedDeque");
    }

    @Test
    public void linkedBlockingDeQueue() throws InterruptedException {
        testStack(LinkedBlockingDeque::new, "linkedBlockingDeque");
    }

    public void testStack(Supplier<Deque<String>> createDeque, String name) throws InterruptedException {
        Set<String> expected = new HashSet<>();
        expected.add("first");
        expected.add("second");

        Set<String> values = new HashSet<>();

        try(AllInterleavings allInterleavings = new AllInterleavings(name)) {
            while (allInterleavings.hasNext()) {
                Deque<String> queue = createDeque.get();
                queue.push("first");
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        queue.push("second");
                    }
                };
                first.start();
                values.add(queue.poll());

                first.join();

            }
            assertThat(values,is(expected));
        }
    }

}
