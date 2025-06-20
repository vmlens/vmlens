package com.vmlens.inttest.interleave.lock;

import com.vmlens.api.AllInterleavings;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BlockingTest {

    //@Test
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
                        secondLock.lock();
                        secondLock.unlock();
                        firstLock.unlock();
                    }
                };
                first.start();
                secondLock.lock();
                boolean lockSuccessful = firstLock.tryLock(100 , TimeUnit.MILLISECONDS);
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


}
