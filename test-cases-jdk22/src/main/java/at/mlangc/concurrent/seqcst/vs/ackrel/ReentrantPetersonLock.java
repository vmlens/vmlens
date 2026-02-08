package at.mlangc.concurrent.seqcst.vs.ackrel;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

class ReentrantPetersonLock extends IndexedLock {
    private final MemoryOrdering memoryOrdering;
    private final AtomicInteger victim = new AtomicInteger(-1);
    private final AtomicIntegerArray locked = new AtomicIntegerArray(2);

    ReentrantPetersonLock() {
        this(MemoryOrdering.VOLATILE);
    }

    ReentrantPetersonLock(MemoryOrdering memoryOrdering) {
        this.memoryOrdering = memoryOrdering;
    }

    public void lock() {
        var idx = ThreadIndex.current();
        var otherIdx = 1 - idx;

        memoryOrdering.set(locked, idx, 1);
        memoryOrdering.set(victim, idx);

        while (memoryOrdering.get(locked, otherIdx) == 1 && memoryOrdering.get(victim) == idx) {
            Thread.onSpinWait();
        }
    }

    public void unlock() {
        int idx = ThreadIndex.current();
        memoryOrdering.set(locked, idx, 0);
    }

    @Override
    int threadLimit() {
        return 2;
    }
}
