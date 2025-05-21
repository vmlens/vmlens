package com.vmlens.test.maven.plugin.concurrent;

import com.vmlens.api.AllInterleavings;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestDelayedQueue {

    private static final class DelayedString implements Delayed {

        private final long startTime;
        private final String text;

        public DelayedString(long startTime, String text) {
            this.startTime = startTime;
            this.text = text;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return startTime - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            DelayedString other = (DelayedString) o;
            return Long.compare(startTime,other.startTime);
        }

        public String text() {
            return text;
        }
    }

    @Ignore
    @Test
    public void testDelayed() throws InterruptedException {
        Set<Integer> expected = new HashSet<>();
        expected.add(1);
        expected.add(2);

        Set<Integer> values = new HashSet<>();

        try(AllInterleavings allInterleavings = new AllInterleavings("delayedQueue")) {
            while (allInterleavings.hasNext()) {
                DelayQueue<DelayedString> queue = new DelayQueue<>();
                queue.add(new DelayedString(System.currentTimeMillis() , "second"));
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        queue.add(new DelayedString(System.currentTimeMillis() , "first"));
                    }
                };
                first.start();
                values.add(values.size());
                first.join();

            }
            assertThat(values,is(expected));
        }
    }
}
