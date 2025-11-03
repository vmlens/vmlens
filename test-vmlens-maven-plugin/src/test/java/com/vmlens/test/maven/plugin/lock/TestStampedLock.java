package com.vmlens.test.maven.plugin.lock;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.StampedLock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestStampedLock {

    private int value = 1;

    @Test
    public void testUpdate() throws InterruptedException {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);

        Set<Integer> actual = new HashSet<>();
        AllInterleavings testUpdate = new AllInterleavings("stampedLockTest");
        while (testUpdate.hasNext()) {
            value = 1;
            final StampedLock lock = new StampedLock();
            Thread first = new Thread() {
                @Override
                public void run() {
                    lock.asReadLock().lock();
                    actual.add(value);
                    lock.asReadLock().unlock();
                }
            };
            first.start();
            lock.asWriteLock().lock();
            value = 2;
            lock.asWriteLock().unlock();
            first.join();
        }
        assertThat(actual,is(expectedSet));
    }


}
