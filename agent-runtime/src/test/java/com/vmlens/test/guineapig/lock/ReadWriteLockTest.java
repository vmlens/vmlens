package com.vmlens.test.guineapig.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    public void update() {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        lock.readLock().lock();

    }


}
