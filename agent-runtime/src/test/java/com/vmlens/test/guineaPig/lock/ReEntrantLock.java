package com.vmlens.test.guineaPig.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReEntrantLock {

    public void update() {
        Lock lock = new ReentrantLock();
        lock.lock();

        lock.unlock();
    }


}
