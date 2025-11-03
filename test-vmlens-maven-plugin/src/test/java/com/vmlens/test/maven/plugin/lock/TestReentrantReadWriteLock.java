package com.vmlens.test.maven.plugin.lock;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestReentrantReadWriteLock {

    private volatile int j;

    @Test
    public void testReadReadLock() throws InterruptedException {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);
        Set<Integer> actual = new HashSet<>();
        final ReadWriteLock lock = new ReentrantReadWriteLock();
        AllInterleavings testUpdate = new AllInterleavings("testReadReadLock");
        while (testUpdate.hasNext()) {
            j = 0;
            Thread first = new Thread() {
                @Override
                public void run() {
                    lock.readLock().lock();
                    j++;
                    lock.readLock().unlock();
                }
            };
            first.start();
            lock.readLock().lock();
            j++;
            lock.readLock().unlock();
            first.join();
            actual.add(j);
        }
        assertThat(actual,is(expectedSet));
    }

    @Test
    public void testReadWriteLock() throws InterruptedException {

        final ReadWriteLock lock = new ReentrantReadWriteLock();
        AllInterleavings testUpdate = new AllInterleavings("testReadWriteLock");
        while (testUpdate.hasNext()) {
            j = 0;
            Thread first = new Thread() {
                @Override
                public void run() {
                    lock.writeLock().lock();
                    j++;
                    lock.writeLock().unlock();
                }
            };
            first.start();
            lock.readLock().lock();
            j++;
            lock.readLock().unlock();
            first.join();
            assertThat(j,is(2));
        }

    }

}
