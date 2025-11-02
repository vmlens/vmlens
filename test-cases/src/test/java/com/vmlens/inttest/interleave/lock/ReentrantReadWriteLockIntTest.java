package com.vmlens.inttest.interleave.lock;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockIntTest {

    private int dataRace;
    private int noDataRace;


    @Test
    public void testDataRace() throws InterruptedException {
        final ReadWriteLock lock = new ReentrantReadWriteLock();
        AllInterleavings testUpdate = new AllInterleavings("testDataRace");
        while (testUpdate.hasNext()) {
            Thread first = new Thread() {
                @Override
                public void run() {
                    lock.readLock().lock();
                    dataRace = 4;
                    lock.readLock().unlock();
                }
            };
            first.start();
            lock.readLock().lock();
            dataRace = 8;
            lock.readLock().unlock();
            first.join();
        }
    }

    @Test
    public void testNoDataRace() throws InterruptedException {

        final ReadWriteLock lock = new ReentrantReadWriteLock();

        AllInterleavings testUpdate = new AllInterleavings("testNoDataRace");

        while (testUpdate.hasNext()) {
            Thread first = new Thread() {
                @Override
                public void run() {
                    lock.readLock().lock();
                    noDataRace = 4;
                    lock.readLock().unlock();
                }
            };
            first.start();
            lock.writeLock().lock();
            noDataRace = 8;
            lock.writeLock().unlock();
            first.join();
        }
    }

}
