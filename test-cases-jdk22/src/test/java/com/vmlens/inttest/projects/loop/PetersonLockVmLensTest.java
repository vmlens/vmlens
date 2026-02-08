package com.vmlens.inttest.projects.loop;

import at.mlangc.concurrent.seqcst.vs.ackrel.MemoryOrdering;
import at.mlangc.concurrent.seqcst.vs.ackrel.PetersonLock;
import at.mlangc.concurrent.seqcst.vs.ackrel.PetersonLockVolatile;
import at.mlangc.concurrent.seqcst.vs.ackrel.ThreadIndex;
import com.vmlens.api.AllInterleavings;
import com.vmlens.api.AllInterleavingsBuilder;
import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.jupiter.api.Test;

/**
 * taken from https://github.com/mlangc/java-snippets
 */
public class PetersonLockVmLensTest {


    @Test
    public void shouldNotDetectProblemWithWorkingLock() throws InterruptedException {
        try (AllInterleavings allInterleavings = new AllInterleavingsBuilder()
                .build("loop.petersonOk")) {
            PetersonLockVolatile lock = new PetersonLockVolatile();
            MutableInt x = new MutableInt();

            while (allInterleavings.hasNext()) {
                Thread first = new Thread(ThreadIndex.toName(0)) {
                    @Override
                    public void run() {
                        lock.lock();
                        x.increment();
                        lock.unlock();
                    }
                };

                Thread second = new Thread(ThreadIndex.toName(1)) {
                    @Override
                    public void run() {
                        lock.lock();
                        x.increment();
                        lock.unlock();
                    }
                };

                first.start();
                second.start();
                second.join();
                first.join();
            }
        }
    }

    @Test
    public void shouldDetectProblemWithBrokenLock() throws InterruptedException {
        try (AllInterleavings allInterleavings = new AllInterleavings("loop.petersonNok")) {
            PetersonLock lock = new PetersonLock(MemoryOrdering.ACQUIRE_RELEASE);
            MutableInt x = new MutableInt();
            while (allInterleavings.hasNext()) {
                Thread first = new Thread(ThreadIndex.toName(0)) {
                    @Override
                    public void run() {
                        lock.lock();
                        x.increment();
                        lock.unlock();
                    }
                };

                Thread second = new Thread(ThreadIndex.toName(1)) {
                    @Override
                    public void run() {
                        lock.lock();
                        x.increment();
                        lock.unlock();
                    }
                };

                first.start();
                second.start();
                second.join();
                first.join();
            }
        }
    }

}
