package com.vmlens.test.maven.plugin;

public class ClassWithVolatileField {

    private volatile int counter;

    public void increment() {
        counter++;
    }

    public int get() {
        return counter;
    }

}
