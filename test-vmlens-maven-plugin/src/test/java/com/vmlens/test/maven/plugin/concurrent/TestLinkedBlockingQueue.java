package com.vmlens.test.maven.plugin.concurrent;

import com.vmlens.api.AllInterleaving;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestLinkedBlockingQueue {

    @Test
    public void testQueue() throws InterruptedException {
        Set<Integer> expected = new HashSet<>();
        expected.add(1);
        expected.add(2);

        Set<Integer> values = new HashSet<>();

        try(AllInterleaving allInterleaving = new AllInterleaving("linkedBlockingQueue")) {
            while (allInterleaving.hasNext()) {
                LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
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

}
