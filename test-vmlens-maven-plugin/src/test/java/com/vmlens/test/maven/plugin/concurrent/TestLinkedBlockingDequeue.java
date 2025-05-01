package com.vmlens.test.maven.plugin.concurrent;

import com.vmlens.api.AllInterleaving;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestLinkedBlockingDequeue {

    @Test
    public void testStack() throws InterruptedException {
        Set<String> expected = new HashSet<>();
        expected.add("first");
        expected.add("second");

        Set<String> values = new HashSet<>();

        try(AllInterleaving allInterleaving = new AllInterleaving("linkedBlockingDeQueue")) {
            while (allInterleaving.hasNext()) {
                LinkedBlockingDeque<String> queue = new LinkedBlockingDeque<>();
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
