package com.vmlens.test.guineapig.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReEntrantLock {

    public void update() {
        Lock lock = new ReentrantLock();
        lock.lock();

        lock.newCondition();
        lock.tryLock();

        lock.unlock();
    }
}
