package com.vmlens.test.guineapig;

public class StaticMethodCallWithSynchronizedBlock {

    public static void method() {
        synchronized (StaticMethodCallWithSynchronizedBlock.class) {

        }
    }

    public void update() {
        method();
    }

}
