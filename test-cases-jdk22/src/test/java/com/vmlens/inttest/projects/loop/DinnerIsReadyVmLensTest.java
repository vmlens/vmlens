package com.vmlens.inttest.projects.loop;

import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * taken from https://github.com/mlangc/java-snippets
 */
public class DinnerIsReadyVmLensTest {
    final AtomicBoolean ready = new AtomicBoolean();
    int dinner;

    @Test
    public void shouldNotDetectProblemIfUpdateIsProperlyReleased() throws InterruptedException {
        try (AllInterleavings allInterleavings = new AllInterleavings("loop.ok")) {
            while (allInterleavings.hasNext()) {
                ready.set(false);
                dinner = 0;

                Thread thread1 = new Thread(() -> {
                    dinner = 42;
                    ready.set(true);
                });


                thread1.start();
                if (ready.get()) {
                    dinner++;
                }
                thread1.join();
            }
        }
    }

    @Test
    public void shouldDetectProblemIfUpdateIsReleasedTooEarly() throws InterruptedException {
        try (AllInterleavings allInterleavings = new AllInterleavings("loop.nok")) {
            while (allInterleavings.hasNext()) {
                ready.set(false);
                dinner = 0;

                Thread thread1 = new Thread(() -> {
                    ready.set(true);
                    dinner = 42;
                });

                thread1.start();
                if (ready.get()) {
                    dinner++;
                }
                thread1.join();
            }
        }
    }
}
