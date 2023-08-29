package com.vmlens.test.volatileFields.fixed;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class TestTwoVolatileFields {


    volatile int j = 0;

    int z = 0;

    //@Test
    public void testUpdate() throws InterruptedException {
        HashSet resultTwo = new HashSet();

        AllInterleavings testUpdate = new AllInterleavings("testTwoVolatileFields");

        while (testUpdate.hasNext()) {


            j = 0;

            Thread first = new Thread() {
                @Override
                public void run() {
                    j++;
                }
            };
            first.start();
            j++;


            z++;

            if (z > 30) {
                return;
            }

            first.join();
            resultTwo.add(j);

        }
        HashSet compare = new HashSet();

        compare.add(1);
        compare.add(2);


        assertEquals(compare, resultTwo);
    }
}
