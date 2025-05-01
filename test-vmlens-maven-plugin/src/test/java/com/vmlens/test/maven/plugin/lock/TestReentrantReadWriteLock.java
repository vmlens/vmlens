package com.vmlens.test.maven.plugin.lock;

import com.vmlens.api.AllInterleaving;
import org.junit.Test;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestReentrantReadWriteLock {

    @Test
    public void testUpdate() throws InterruptedException {

        int count = 0;
        final ReadWriteLock lock = new ReentrantReadWriteLock();

        AllInterleaving testUpdate = new AllInterleaving("readWriteLockTest");
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
            count++;
        }
        assertThat(count,is(3));
    }

}
