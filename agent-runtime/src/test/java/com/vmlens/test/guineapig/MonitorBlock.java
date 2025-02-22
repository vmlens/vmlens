package com.vmlens.test.guineapig;

public class MonitorBlock {

    public void update() throws InterruptedException {
        synchronized (this) {

        }
    }
}
