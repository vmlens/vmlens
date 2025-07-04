package com.vmlens.inttest.nonvolatile;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

public class ArrayTest {

    private final int[] array = new int[7];

    @Test
    public void testUpdate() throws InterruptedException {
        array[6] = 0;

        AllInterleavings testUpdate = new AllInterleavings("arrayTest");
        while (testUpdate.hasNext()) {
            Thread first = new Thread() {
                @Override
                public void run() {
                    array[6] = 3;
                }
            };
            first.start();
            int i =  array[6];
            first.join();
        }
    }

}
