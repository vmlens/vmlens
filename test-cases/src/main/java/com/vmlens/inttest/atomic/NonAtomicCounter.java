package com.vmlens.inttest.atomic;

public class NonAtomicCounter {

    private volatile int count;

    public int incrementAndGet() {
        return count++;
    }

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }

}

