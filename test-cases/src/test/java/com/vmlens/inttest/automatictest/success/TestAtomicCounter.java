package com.vmlens.inttest.automatictest.success;

import com.vmlens.api.testbuilder.AtomicTestBuilder;
import com.vmlens.inttest.atomic.AtomicCounter;
import com.vmlens.inttest.atomic.NonAtomicCounter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestAtomicCounter {

    @Test
    public void testUpdate() throws InterruptedException {
            new AtomicTestBuilder<>(AtomicCounter::new)
                    .addUpdate(AtomicCounter::incrementAndGet)
                    .addWrite(AtomicCounter::increment)
                    .addReadOnly(AtomicCounter::getCount)
                    .withParallelMethodCalls(3)
                    .runTests();
    }
}
