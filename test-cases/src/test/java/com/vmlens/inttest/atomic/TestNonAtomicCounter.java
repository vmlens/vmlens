package com.vmlens.inttest.atomic;

import com.vmlens.api.atomic.AtomicTestBuilder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestNonAtomicCounter {

    @Test
    public void testUpdate() throws InterruptedException {
        RuntimeException actualException = null;
        try {
            new AtomicTestBuilder<>(NonAtomicCounter::new)
                    .addUpdate(NonAtomicCounter::incrementAndGet)
                    .addWrite(NonAtomicCounter::increment)
                    .addReadOnly(NonAtomicCounter::getCount)
                    .runTests();
        }
        catch (RuntimeException exp) {
            actualException = exp;
        }
        assertThat(actualException, instanceOf(RuntimeException.class));
    }
}
