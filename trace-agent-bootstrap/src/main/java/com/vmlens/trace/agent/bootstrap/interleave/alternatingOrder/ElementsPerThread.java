package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

public class ElementsPerThread<ELEMENT> {

    private final ELEMENT[] array;
    private int currentPosition;

    public ElementsPerThread(ELEMENT[] array) {
        this.array = array;
        reset();
    }

    public ELEMENT first() {
        return array[currentPosition];
    }

    public ELEMENT removeFirst() {
        ELEMENT temp = array[currentPosition];
        currentPosition++;
        return temp;
    }

    public boolean isEmpty() {
        return currentPosition >= array.length;
    }

    public void reset() {
        currentPosition = 0;
    }

}
