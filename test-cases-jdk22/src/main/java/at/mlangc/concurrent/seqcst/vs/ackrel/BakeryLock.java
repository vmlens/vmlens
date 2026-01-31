package at.mlangc.concurrent.seqcst.vs.ackrel;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongArray;

class BakeryLock extends IndexedLock {
    private final MemoryOrdering memoryOrdering;
    private final AtomicIntegerArray interested;
    private final AtomicLongArray tickets;

    BakeryLock(int nThreads, MemoryOrdering memoryOrdering) {
        this.memoryOrdering = memoryOrdering;
        this.interested = new AtomicIntegerArray(nThreads);
        this.tickets = new AtomicLongArray(nThreads);
    }

    @Override
    int threadLimit() {
        return interested.length();
    }

    @Override
    public void lock() {
        int myIdx = ThreadIndex.current();
        memoryOrdering.set(interested, myIdx, 1);

        long myTicket = 0L;
        for (int i = 0; i < tickets.length(); i++) {
            myTicket = Math.max(myTicket, memoryOrdering.get(tickets, i));
        }
        myTicket++;
        memoryOrdering.set(tickets, myIdx, myTicket);

        while (true) {
            boolean success = true;
            for (int oIdx = 0; oIdx < interested.length(); oIdx++) {
                if (oIdx != myIdx && memoryOrdering.get(interested, oIdx) == 1) {
                    long oTicket = memoryOrdering.get(tickets, oIdx);
                    if (oTicket < myTicket || (oTicket == myTicket && oIdx < myIdx)) {
                        success = false;
                        break;
                    }
                }
            }

            if (success) {
                break;
            } else {
                Thread.onSpinWait();
            }
        }
    }

    @Override
    public void unlock() {
        memoryOrdering.set(interested, ThreadIndex.current(), 0);
    }
}
