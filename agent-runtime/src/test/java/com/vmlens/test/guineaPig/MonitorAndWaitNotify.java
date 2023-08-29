package com.vmlens.test.guineaPig;

public class MonitorAndWaitNotify {

    public void update() throws InterruptedException {
        synchronized (this) {
            this.notify();
            this.wait(1);
        }
    }
}
