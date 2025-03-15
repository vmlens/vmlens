package com.vmlens.test.guineapig;

public class WaitNotify {

    public void update() throws InterruptedException {
        synchronized (this) {
            this.wait(5);
            this.notify();
        }
    }

}
