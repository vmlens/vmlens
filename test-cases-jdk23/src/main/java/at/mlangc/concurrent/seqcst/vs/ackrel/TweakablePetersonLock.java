package at.mlangc.concurrent.seqcst.vs.ackrel;

import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

class TweakablePetersonLock extends IndexedLock {
    private final MemoryOrdering memoryOrdering;
    private final AtomicIntegerArray interested = new AtomicIntegerArray(2);
    private final AtomicInteger turn = new AtomicInteger();
    private final boolean useFence;

    TweakablePetersonLock(MemoryOrdering memoryOrdering, boolean useFence) {
        this.memoryOrdering = memoryOrdering;
        this.useFence = useFence;
    }

    @Override
    int threadLimit() {
        return 2;
    }

    @Override
    public void lock() {
        var myIdx = ThreadIndex.current();
        var oIdx = 1 - myIdx;

        memoryOrdering.set(interested, myIdx, 1);
        if (useFence) VarHandle.fullFence();
        memoryOrdering.set(turn, oIdx);

        while (memoryOrdering.get(interested, oIdx) == 1 && memoryOrdering.get(turn) != myIdx) {
            Thread.onSpinWait();
        }
    }

    @Override
    public void unlock() {
        memoryOrdering.set(interested, ThreadIndex.current(), 0);
    }
}
