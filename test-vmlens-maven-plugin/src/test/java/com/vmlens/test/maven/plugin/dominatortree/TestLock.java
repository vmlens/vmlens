package com.vmlens.test.maven.plugin.dominatortree;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestLock {

    private int i = 0;
    private int j = 0;
    private volatile int x = 0;
    private final Lock firstLock = new ReentrantLock();
    private final Lock secondLock = new ReentrantLock();

    @Test
    public void testLockChaining() throws InterruptedException {
        AllInterleavings testUpdate = new AllInterleavings("dominatorTreeLockChaining");
        while (testUpdate.hasNext()) {
            i = 0;
            j = 0;
            Thread first = new Thread() {
                @Override
                public void run() {
                    increment();
                }
            };
            first.start();
            increment();
            first.join();
            assertThat(j,is(2));
        }
    }

    private void increment() {
        firstLock.lock();
        j++;
        secondLock.lock();
        firstLock.unlock();
        i++;
        secondLock.unlock();

    }


    @Test
    public void testNormalLock() throws InterruptedException {
        AllInterleavings testUpdate = new AllInterleavings("dominatorTreeNormalLock");
        while (testUpdate.hasNext()) {
            j = 0;
            Thread first = new Thread() {
                @Override
                public void run() {
                    firstLock.lock();
                    j++;
                    firstLock.unlock();
                }
            };
            first.start();
            firstLock.lock();
            j++;
            firstLock.unlock();
            first.join();
            assertThat(j,is(2));
        }
    }

    @Test
    public void testInsideOutside() throws InterruptedException {
        AllInterleavings testUpdate = new AllInterleavings("dominatorTreeLockInsideOutside");
        while (testUpdate.hasNext()) {
            x = 0;
            Thread first = new Thread() {
                @Override
                public void run() {
                    firstLock.lock();
                    x++;
                    firstLock.unlock();
                }
            };
            first.start();
            firstLock.lock();
            x++;
            firstLock.unlock();
            x++;
            first.join();
        }
    }
    
    

}
