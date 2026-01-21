package com.vmlens.test.maven.plugin.lock;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestReentrant {

    private int i = 0;

    @Test
    public void testLock() throws InterruptedException {
        try(AllInterleavings allInterleavings = new AllInterleavings("reentrantLock")) {
        while (allInterleavings.hasNext()) {
            final ReentrantLock lock = new ReentrantLock();
            Thread first = new Thread() {
                    @Override
                    public void run() {
                        lock.lock();
                        i++;
                        lock.unlock();
                    }
                };
            first.start();
            lock.lock();
            i++;
            lock.unlock();
            first.join();
            }
        }
    }

    /* This test is an example of a tst with condition, varying interleave actions
     */
    //@Test
    public void testTryLock() throws InterruptedException {
        Set<Boolean> expectedSet = new HashSet<>();
        expectedSet.add(true);
        expectedSet.add(false);

        Set<Boolean> actualMain = new HashSet<>();
        Set<Boolean> actualThread = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("reentrantTryLock")) {
            while (allInterleavings.hasNext()) {
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
                        actualThread.add(lockSuccessful);
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
                actualMain.add(lockSuccessful);
                first.join();
            }

            Set<Boolean> actual = new HashSet<>();
            actual.addAll(actualMain);
            actual.addAll(actualThread);
            assertThat(actual,is(expectedSet));
        }
    }

    /* This test is an example of a tst with condition, varying interleave actions
     */
    //@Test
    public void testTryLockWithTimeout() throws InterruptedException {
        Set<Boolean> expectedSet = new HashSet<>();
        expectedSet.add(true);
        expectedSet.add(false);

        // Currently deadlock is not symmetric,
        // so only one try will fail

        Set<Boolean> actualMain = new HashSet<>();
        Set<Boolean> actualThread = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("reentrantTryLockWithTimeout")) {
            while (allInterleavings.hasNext()) {
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
                        actualThread.add(lockSuccessful);
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
                actualMain.add(lockSuccessful);
                first.join();
            }
            Set<Boolean> actual = new HashSet<>();
            actual.addAll(actualMain);
            actual.addAll(actualThread);
            assertThat(actual,is(expectedSet));
        }
    }

}
