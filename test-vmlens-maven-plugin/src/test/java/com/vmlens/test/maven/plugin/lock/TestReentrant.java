package com.vmlens.test.maven.plugin.lock;

import com.vmlens.api.AllInterleaving;
import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestReentrant {

    @Test
    public void testUpdate() throws InterruptedException {
        int count = 0;

        AllInterleaving testUpdate = new AllInterleaving("reentrantLock");
        while (testUpdate.hasNext()) {
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
            count++;
        }
    }
}
