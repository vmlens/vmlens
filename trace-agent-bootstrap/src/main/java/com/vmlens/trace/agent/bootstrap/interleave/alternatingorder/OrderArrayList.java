package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.PositionOrder;
import com.vmlens.trace.agent.bootstrap.interleave.PositionOrderComparator;

import java.util.Arrays;
import java.util.Iterator;

public class OrderArrayList {

    private final int chunkSize;
    private final LeftBeforeRight[] fixedArray;
    private LeftBeforeRight[] array;
    private int length;

    private class OrderIterator implements Iterator<LeftBeforeRight> {

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

    public PositionOrder[] withInverse() {
        PositionOrder[] sort = new  PositionOrder[2 * length];
        int index = 0;
        for(int i =0; i < length; i++) {
            sort[index] = array[i];
            sort[index + 1] = array[i].inverse();
            index +=2 ;
        }
        Arrays.sort(sort,new PositionOrderComparator() );
        return sort;
    }

    public int length() {
        return length;
    }

    public LeftBeforeRight get(int index) {
        return array[index];
    }

    public Iterator<LeftBeforeRight> iterator() {
        return new OrderIterator();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
       for(int i = 0; i < length; i++)  {
            result.append( array[i].toString()).append("\n");
        }
        return result.toString();
    }
}
