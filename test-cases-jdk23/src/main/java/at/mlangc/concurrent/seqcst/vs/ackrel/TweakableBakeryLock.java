package at.mlangc.concurrent.seqcst.vs.ackrel;

import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongArray;

class TweakableBakeryLock extends IndexedLock {
    private final MemoryOrdering memoryOrdering;
    private final boolean useFence;
    private final AtomicIntegerArray interested;
    private final AtomicLongArray tickets;

    TweakableBakeryLock(int maxThreads, MemoryOrdering memoryOrdering, boolean useFence) {
        this.memoryOrdering = memoryOrdering;
        this.useFence = useFence;
        this.interested = new AtomicIntegerArray(maxThreads);
        this.tickets = new AtomicLongArray(maxThreads);
    }

    @Override
    int threadLimit() {
        return interested.length();
    }

    @Override
    public void lock() {
        var idx = ThreadIndex.current();

        memoryOrdering.set(interested, idx, 1);

        if (useFence) VarHandle.fullFence();

        var ticket = 0L;
        for (int i = 0; i < tickets.length(); i++) {
            ticket = Math.max(ticket, memoryOrdering.get(tickets, i));
        }
        ticket++;

        memoryOrdering.set(tickets, idx, ticket);

        while (true) {
            boolean locked = true;
            for (int i = 0; i < tickets.length(); i++) {
                if (i != idx && memoryOrdering.get(interested, i) == 1) {
                    var otherTicket = memoryOrdering.get(tickets, i);
                    if (otherTicket < ticket || otherTicket == ticket && i < idx) {
                        locked = false;
                        break;
                    }
                }
            }

            if (locked) {
                break;
            } else {
                Thread.onSpinWait();
            }
        }
    }

    @Override
    public void unlock() {
        var idx = ThreadIndex.current();
        memoryOrdering.set(interested, idx, 0);
    }
}
