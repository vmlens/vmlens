package com.vmlens.inttest.interleave.atomic;

import com.vmlens.api.AllInterleavings;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class AtomicIntegerIntTest {

    //@Test
    public void testNotYetImplemented() throws InterruptedException {
        UnsupportedOperationException exception = null;
        try(AllInterleavings testUpdate = new AllInterleavings("atomicIntegerTest")) {
            try {
                while (testUpdate.hasNext()) {
                    new AtomicInteger().getAcquire();
                }
            } catch (UnsupportedOperationException unsupportedOperationException) {
                exception = unsupportedOperationException;
            }
        }
        assertThat(exception,is(notNullValue()));
    }

}
