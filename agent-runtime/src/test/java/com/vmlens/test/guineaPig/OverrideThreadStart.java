package com.vmlens.test.guineaPig;

public class OverrideThreadStart extends Thread {

    @Override
    public synchronized void start() {
        super.start();
    }
}
