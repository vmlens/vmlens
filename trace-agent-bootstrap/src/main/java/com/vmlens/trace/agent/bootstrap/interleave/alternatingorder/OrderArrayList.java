package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;

public class OrderArrayList {

    private final int chunkSize;
    private final LeftBeforeRight[] fixedArray;
    private LeftBeforeRight[] array;
    private int length;

    public OrderArrayList(LeftBeforeRight[] fixedArray) {
        this(16,fixedArray);
    }

    public OrderArrayList(int chunkSize, LeftBeforeRight[] fixedArray) {
        this.chunkSize = chunkSize;
        this.fixedArray = fixedArray;
        this.array = new LeftBeforeRight[fixedArray.length + chunkSize];
        reset();
    }

    public void add(LeftBeforeRight leftBeforeRight) {
        if(length >= array.length) {
            LeftBeforeRight[] newArray = new LeftBeforeRight[array.length+chunkSize];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[length] = leftBeforeRight;
        length++;
    }

    public void reset() {
        length = fixedArray.length;
        System.arraycopy(fixedArray, 0, array, 0, fixedArray.length);
    }

    public int length() {
        return length;
    }

    public void setToNull(int index) {
        array[index] = null;
    }

    public LeftBeforeRight get(int index) {
        return array[index];
    }

}
