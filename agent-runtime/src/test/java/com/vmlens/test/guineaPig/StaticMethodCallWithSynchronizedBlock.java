package com.vmlens.test.guineaPig;

public class StaticMethodCallWithSynchronizedBlock {

    public static void method() {
        synchronized (StaticMethodCallWithSynchronizedBlock.class) {

        }
    }

    public void update() {
        method();
    }

}
