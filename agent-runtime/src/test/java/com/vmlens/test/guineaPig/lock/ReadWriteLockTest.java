package com.vmlens.test.guineaPig.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    public void update() {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        lock.readLock().lock();

    }


}
