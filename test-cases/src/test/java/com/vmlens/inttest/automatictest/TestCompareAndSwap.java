package com.vmlens.inttest.automatictest;

import com.vmlens.api.AllInterleavings;
import com.vmlens.test.maven.plugin.automatictest.CompareAndSwap;
import org.junit.Test;

import static com.vmlens.api.Runner.runParallel;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

public class TestCompareAndSwap {
    @Test
    public void testUpdate() throws InterruptedException {
        int runs = 0;
        AllInterleavings testUpdate = new AllInterleavings("testCompareAndSwap");
        while (testUpdate.hasNext()) {
            CompareAndSwap compareAndSwap = new CompareAndSwap();
            runParallel(
                    () -> compareAndSwap.multiply(2),
                    () -> compareAndSwap.multiply(2),
                    () -> compareAndSwap.multiply(2));
            runs++;
            assertThat(compareAndSwap.intValue(),is(2*2*2));

        }
        assertThat(runs,lessThan(10));
    }

}
