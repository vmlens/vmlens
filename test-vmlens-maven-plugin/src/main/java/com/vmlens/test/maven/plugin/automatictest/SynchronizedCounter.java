package com.vmlens.test.maven.plugin.automatictest;

public class SynchronizedCounter {

    private int count;

    public synchronized void increment() {
        count++;
    }

    public synchronized int get() {
        return count;
    }


}
