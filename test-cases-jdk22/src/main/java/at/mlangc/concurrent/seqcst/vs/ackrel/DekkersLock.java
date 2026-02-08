package at.mlangc.concurrent.seqcst.vs.ackrel;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

class DekkersLock extends IndexedLock {
    private final MemoryOrdering memoryOrdering;
    private final AtomicIntegerArray wantsLock = new AtomicIntegerArray(2);
    private final AtomicInteger turn = new AtomicInteger(0);

    DekkersLock() {
        this(MemoryOrdering.VOLATILE);
    }

    DekkersLock(MemoryOrdering memoryOrdering) {
        this.memoryOrdering = memoryOrdering;
    }

    @Override
    int threadLimit() {
        return 2;
    }

    @Override
    public void lock() {
        int idx = ThreadIndex.current();
        int otherIdx = 1 - idx;

        memoryOrdering.set(wantsLock, idx, 1);
        while (memoryOrdering.get(wantsLock, otherIdx) == 1) {
            if (memoryOrdering.get(turn) != idx) {
                memoryOrdering.set(wantsLock, idx, 0);

                while (memoryOrdering.get(turn) != idx) {
                    Thread.onSpinWait();
                }

                memoryOrdering.set(wantsLock, idx, 1);
            }
        }
    }

    @Override
    public void unlock() {
        int idx = ThreadIndex.current();
        int otherIdx = 1 - idx;

        memoryOrdering.set(turn, otherIdx);
        memoryOrdering.set(wantsLock, idx, 0);
    }
}
