package com.vmlens.test.maven.plugin.automatictest;

import com.vmlens.api.automatic.AutomaticTestBuilder;
import org.junit.Test;

public class TestCompareAndSwap {

    @Test
    public void testAutomatic() {
        new AutomaticTestBuilder<>(CompareAndSwap::new)
                .addWrite((obj) -> obj.multiply(5) )
                .addReadOnly(CompareAndSwap::intValue)
                .runTests();
    }
}
