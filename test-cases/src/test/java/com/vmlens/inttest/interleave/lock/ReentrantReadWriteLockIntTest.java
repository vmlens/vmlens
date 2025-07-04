package com.vmlens.inttest.interleave.lock;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockIntTest {

    @Test
    public void testUpdate() throws InterruptedException {

        final ReadWriteLock lock = new ReentrantReadWriteLock();

        AllInterleavings testUpdate = new AllInterleavings("readWriteLockTest");

        while (testUpdate.hasNext()) {
            Thread first = new Thread() {
                @Override
                public void run() {
                    lock.readLock().lock();
                    lock.readLock().unlock();
                }
            };
            first.start();
            lock.writeLock().lock();
            lock.writeLock().unlock();
            first.join();
        }
    }

}
