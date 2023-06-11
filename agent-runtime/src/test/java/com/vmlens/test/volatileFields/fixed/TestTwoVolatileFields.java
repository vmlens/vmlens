package com.vmlens.test.volatileFields.fixed;

import com.vmlens.api.AllInterleavings;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class TestTwoVolatileFields {

    volatile int i = 0;
    volatile int j = 0;

    public void testUpdate() throws InterruptedException {
        int x = 0;
        HashSet result = new HashSet();
        HashSet resultTwo = new HashSet();

        AllInterleavings testUpdate = new AllInterleavings("testTwoVolatileFields");

        while (testUpdate.hasNext()) {
            x++;
            i = 0;
            j = 0;

            Thread first = new Thread() {
                @Override
                public void run() {
                    i++;
                    j++;
                }
            };
            //Thread second = new Thread( () ->   {   } );
            first.start();
            j++;
            i++;


            first.join();
            //second.join();

            result.add(i);
            resultTwo.add(j);

        }

//			System.out.println(x);


        HashSet compare = new HashSet();

        compare.add(1);
        compare.add(2);


        assertEquals(compare, result);
        assertEquals(compare, resultTwo);
    }
}
