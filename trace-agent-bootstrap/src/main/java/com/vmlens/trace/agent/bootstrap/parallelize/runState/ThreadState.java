package com.vmlens.trace.agent.bootstrap.parallelize.runState;

// ToDo stack basiert für fork join
public class ThreadState {

    private final int index;
    private int currentPosition;

    public ThreadState(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public int getAndIncrementPosition() {
        int temp = currentPosition;
        currentPosition++;
        return temp;
    }

}