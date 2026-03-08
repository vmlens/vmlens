package com.vmlens.test.maven.plugin.lock;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static com.vmlens.api.Runner.runParallel;


public class TestCondition {

    private boolean flag = false;

    @Test
    public void testLock() throws Throwable {
        try(AllInterleavings allInterleavings = new AllInterleavings("testCondition")) {
            while (allInterleavings.hasNext()) {
                flag = false;
                final ReentrantLock lock = new ReentrantLock();
                final Condition condition = lock.newCondition();
                runParallel(
                        () -> {
                            lock.lock();
                            flag = true;
                            condition.signal();
                            lock.unlock();
                        },
                        () -> {
                            lock.lock();
                            while(! flag) {
                                try {
                                    condition.await();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            lock.unlock(); }
                );
            }
        }
    }

}
