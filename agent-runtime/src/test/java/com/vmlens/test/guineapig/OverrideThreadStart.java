package com.vmlens.test.guineapig;

public class OverrideThreadStart extends Thread {

    @Override
    public synchronized void start() {
        super.start();
    }
}
