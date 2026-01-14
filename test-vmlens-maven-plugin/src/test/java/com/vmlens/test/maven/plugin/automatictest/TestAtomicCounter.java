package com.vmlens.test.maven.plugin.automatictest;

import com.vmlens.api.atomic.AtomicTestBuilder;
import org.junit.Test;

public class TestAtomicCounter {

    @Test
    public void testAutomatic() {
        new AtomicTestBuilder<>(AtomicCounter::new)
                .addWrite(AtomicCounter::increment)
                .addReadOnly(AtomicCounter::get)
                .runTests();
    }


}
