package com.vmlens.inttest;

import com.vmlens.api.AllInterleavings;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.vmlens.api.Runner.runParallel;

public class TestLiveLock {

    @Ignore
    @Test
    public void testLiveLock() throws InterruptedException {
        AllInterleavings testUpdate = new AllInterleavings("testLiveLock");
        while (testUpdate.hasNext()) {
            Lock first = new ReentrantLock(true);
            Lock second = new ReentrantLock(true);
            runParallel(
                    () -> {
                        while (true) {
                            first.lock();
                            if (second.tryLock()) {
                                break;
                            }
                            first.unlock();
                        }
                        first.unlock();
                        second.unlock();
                    },
                    () -> {
                        while (true) {
                            second.lock();
                            if (first.tryLock()) {
                                break;
                            }
                            second.unlock();
                        }
                        second.unlock();
                        first.unlock();
                    });
        }

    }
}
