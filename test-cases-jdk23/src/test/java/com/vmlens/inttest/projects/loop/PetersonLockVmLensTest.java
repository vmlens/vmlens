package com.vmlens.inttest.projects.loop;

import at.mlangc.concurrent.seqcst.vs.ackrel.MemoryOrdering;
import at.mlangc.concurrent.seqcst.vs.ackrel.PetersonLock;
import at.mlangc.concurrent.seqcst.vs.ackrel.ThreadIndex;
import com.vmlens.api.AllInterleavings;
import com.vmlens.api.AllInterleavingsBuilder;
import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.jupiter.api.Test;

/**
 * taken from https://github.com/mlangc/java-snippets
 */
public class PetersonLockVmLensTest {

    /**
     * here is a data race, see https://janonsoftware.blogspot.com/2013/03/petersons-locking-algorithm-in-java.html
     * for an explanation
     */
    @Test
    public void shouldNotDetectProblemWithWorkingLock() throws InterruptedException {
        try (AllInterleavings allInterleavings = new AllInterleavingsBuilder()
                .build("loop.petersonOk")) {
            PetersonLock lock = new PetersonLock(MemoryOrdering.VOLATILE);
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
