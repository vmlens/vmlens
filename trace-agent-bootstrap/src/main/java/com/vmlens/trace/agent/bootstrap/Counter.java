package com.vmlens.trace.agent.bootstrap;

public class Counter {

    private int count;

    public Counter() {
        this.count = 1;
    }

    public void increment() {
        count++;
    }

    public int count() {
        return count;
    }
}
