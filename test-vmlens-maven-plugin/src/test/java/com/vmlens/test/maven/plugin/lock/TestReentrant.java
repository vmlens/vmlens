package com.vmlens.test.maven.plugin.lock;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestReentrant {

    @Test
    public void testLock() throws InterruptedException {
        try(AllInterleavings allInterleaving = new AllInterleavings("reentrantLock")) {
        while (allInterleaving.hasNext()) {
            final ReentrantLock lock = new ReentrantLock();
            Thread first = new Thread() {
                    @Override
                    public void run() {
                        lock.lock();
                        lock.unlock();
                    }
                };
            first.start();
            lock.lock();
            lock.unlock();
            first.join();
            }
        }
    }

    /* This test is an example of a tst with condition, varying interleave actions
     */
    @Test
    public void testTryLock() throws InterruptedException {
        Set<Boolean> expectedSet = new HashSet<>();
        expectedSet.add(true);
        expectedSet.add(false);

        Set<Boolean> actual = new HashSet<>();
        try(AllInterleavings allInterleaving = new AllInterleavings("reentrantTryLock")) {
            while (allInterleaving.hasNext()) {
                final ReentrantLock firstLock = new ReentrantLock();
                final ReentrantLock secondLock = new ReentrantLock();
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        firstLock.lock();
                        boolean lockSuccessful = secondLock.tryLock();
                        if(lockSuccessful) {
                            secondLock.unlock();
                        }
                        firstLock.unlock();
                    }
                };
                first.start();
                secondLock.lock();
                boolean lockSuccessful = firstLock.tryLock();
                if(lockSuccessful) {
                    firstLock.unlock();
                }
                secondLock.unlock();
                actual.add(lockSuccessful);
                first.join();
            }
            assertThat(actual,is(expectedSet));
        }
    }

    /* This test is an example of a tst with condition, varying interleave actions
     */
    @Test
    public void testTryLockWithTimeout() throws InterruptedException {
        Set<Boolean> expectedSet = new HashSet<>();
        expectedSet.add(true);
        expectedSet.add(false);

        Set<Boolean> actual = new HashSet<>();
        try(AllInterleavings allInterleaving = new AllInterleavings("reentrantTryLockWithTimeout")) {
            while (allInterleaving.hasNext()) {
                final ReentrantLock firstLock = new ReentrantLock();
                final ReentrantLock secondLock = new ReentrantLock();
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        firstLock.lock();
                        boolean lockSuccessful = secondLock.tryLock();
                        if (lockSuccessful) {
                            secondLock.unlock();
                        }
                        firstLock.unlock();
                    }
                };
                first.start();
                secondLock.lock();
                boolean lockSuccessful = firstLock.tryLock();
                if (lockSuccessful) {
                    firstLock.unlock();
                }
                secondLock.unlock();
                actual.add(lockSuccessful);
                first.join();
            }
            assertThat(actual,is(expectedSet));
        }
    }

}
