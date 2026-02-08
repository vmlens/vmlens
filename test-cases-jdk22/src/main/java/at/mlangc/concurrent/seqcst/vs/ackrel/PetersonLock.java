package at.mlangc.concurrent.seqcst.vs.ackrel;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class PetersonLock extends IndexedLock {
    private final MemoryOrdering memoryOrdering;
    private final AtomicIntegerArray interested = new AtomicIntegerArray(2);
    private final AtomicInteger turn = new AtomicInteger();

    public PetersonLock(MemoryOrdering memoryOrdering) {
        this.memoryOrdering = memoryOrdering;
    }

    @Override
    public void lock() {
        var myIdx = ThreadIndex.current();
        var oIdx = 1 - myIdx;

        memoryOrdering.set(interested, myIdx, 1);
        memoryOrdering.set(turn, myIdx);

        while (memoryOrdering.get(interested, oIdx) == 1 && memoryOrdering.get(turn) != myIdx) {
            Thread.onSpinWait();
        }
    }

    @Override
    public void unlock() {
        memoryOrdering.set(interested, ThreadIndex.current(), 0);
    }

    @Override
    int threadLimit() {
        return 2;
    }
}
