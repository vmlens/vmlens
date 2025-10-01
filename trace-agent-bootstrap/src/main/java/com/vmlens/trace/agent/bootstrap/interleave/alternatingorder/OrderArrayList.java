package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;

import java.util.Iterator;

public class OrderArrayList {

    private final int chunkSize;
    private final LeftBeforeRight[] fixedArray;
    private LeftBeforeRight[] array;
    private int length;

    private class AlternatingOrderIterator implements Iterator<LeftBeforeRight> {

        private int index;

        @Override
        public boolean hasNext() {
            return index < length;
        }

        @Override
        public LeftBeforeRight next() {
            int temp = index;
            index++;
            return array[temp];
        }
    }

    private class AllOrderIterator implements Iterator<LeftBeforeRight> {

        private int index;

        @Override
        public boolean hasNext() {
            return index < (length + fixedArray.length);
        }

        @Override
        public LeftBeforeRight next() {
            int temp = index;
            index++;

            if(temp < fixedArray.length) {
                return fixedArray[temp];
            }

            return array[temp - fixedArray.length];
        }
    }

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

    public LeftBeforeRight get(int index) {
        return array[index];
    }

    public Iterator<LeftBeforeRight> alternatingOrderIterator() {
        return new AlternatingOrderIterator();
    }

    public Iterator<LeftBeforeRight> allOrderIterator() {
        return new AlternatingOrderIterator();
    }

}
