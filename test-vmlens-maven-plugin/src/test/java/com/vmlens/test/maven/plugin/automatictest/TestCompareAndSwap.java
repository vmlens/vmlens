package com.vmlens.test.maven.plugin.automatictest;

import com.vmlens.api.atomic.AtomicTestBuilder;
import org.junit.Test;

public class TestCompareAndSwap {

    @Test
    public void testAutomatic() {
        new AtomicTestBuilder<>(CompareAndSwap::new)
                .addWrite((obj) -> obj.multiply(5) )
                .addReadOnly(CompareAndSwap::intValue)
                .runTests();
    }
}
