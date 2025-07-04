package com.vmlens.inttest.interleave;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

public class ExceptionTest {

    private volatile int j = 0;

    @Test
    public void testUpdate() throws InterruptedException {
        AllInterleavings testUpdate = new AllInterleavings("exceptionTest");

        while (testUpdate.hasNext()) {
            Thread first = new Thread() {
                @Override
                public void run() {
                    firstMethod();
                }
            };
            first.start();
            j++;
            first.join();
        }
    }

    private void firstMethod() {
        try {
            j++;
            secondMethod();
        }
         catch(RuntimeException exp) {

        }

    }

    private void secondMethod() {
        methodWithException();
    }

    private void methodWithException() {
        j++;
        throw new RuntimeException("test");
    }


}
