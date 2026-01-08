package com.vmlens.inttest.atomic;

import com.vmlens.api.atomic.AtomicTestBuilder;
import org.junit.Test;

public class TestNonAtomicCounter {

    @Test
    public void testUpdate() throws InterruptedException {
        new AtomicTestBuilder<>(NonAtomicCounter::new)
                .addUpdate(NonAtomicCounter::incrementAndGet)
                .addWrite(NonAtomicCounter::increment)
                .addReadOnly(NonAtomicCounter::getCount)
                .build().runTests();
    }
}
