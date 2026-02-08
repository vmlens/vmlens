package com.vmlens.test.maven.plugin.automatictest;

import com.vmlens.api.atomic.AtomicTestBuilder;
import org.junit.Test;

public class TestSynchronizedCounter {

    @Test
    public void testAutomatic() {
        new AtomicTestBuilder<>(SynchronizedCounter::new)
                .addWrite(SynchronizedCounter::increment)
                .addReadOnly(SynchronizedCounter::get)
                .runTests();
    }


}
