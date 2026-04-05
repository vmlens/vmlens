package com.vmlens.inttest.atomic;

public class AtomicCounter {

    private volatile int count;

    public synchronized int incrementAndGet() {
        return count++;
    }

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }

}
