package com.vmlens.inttest.projects.jpos;

import com.vmlens.api.AllInterleavings;
import org.jpos.transaction.Context;
import org.junit.jupiter.api.Test;

public class ContextConcurrentTest {

    @Test
    public void testResumeAndPause() throws Throwable {
        try (AllInterleavings allInterleavings = new AllInterleavings("ContextConcurrentTest.testResetAndPause")) {
            while (allInterleavings.hasNext()) {
                Context context = new Context();
                Thread first = new Thread(() -> context.resume(5));
                first.start();
                context.pause();
                first.join();
            }
        }
    }

    /**
     * here the exit reenatrant get lost
     */
    @Test
    public void testResumeResumeAndPause() throws Throwable {
        try (AllInterleavings allInterleavings = new AllInterleavings("ContextConcurrentTest.testResumeResumeAndPause")) {
            while (allInterleavings.hasNext()) {
                Context context = new Context();
                Thread first = new Thread(() ->  context.resume(5));
                first.start();
                context.resume(3);
                context.pause();
                first.join();
            }
        }
    }
}
