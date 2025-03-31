package com.vmlens.test.guineapig;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ArrayAccess {

    public void update() throws InterruptedException {
        int[] array =new int[50];
        array[6] = 13;

        Object[] objectArray = new Object[20];
        objectArray[8] = "test";

        Object obj = objectArray[3];

        long[] longArray = new long[50];
        longArray[6] = 7L;

        double[] doubleArray = new double[10];
        doubleArray[3] = 5d;

        assertThat(longArray[6],is(7L));
        assertThat(doubleArray[3],is(5d));


    }
}
