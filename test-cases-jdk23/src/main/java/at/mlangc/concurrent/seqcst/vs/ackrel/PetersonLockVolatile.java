package at.mlangc.concurrent.seqcst.vs.ackrel;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class PetersonLockVolatile {

    private final AtomicIntegerArray interested = new AtomicIntegerArray(2);
    private final AtomicInteger turn = new AtomicInteger();

    public PetersonLockVolatile() {
    }

    public void lock() {
        var myIdx = ThreadIndex.current();
        var oIdx = 1 - myIdx;
        interested.set( myIdx, 1);
        turn.set(oIdx);
        while (interested.get( oIdx) == 1 && turn.get() != myIdx) {
            Thread.onSpinWait();
        }
    }

    public void unlock() {
        interested.set( ThreadIndex.current(), 0);
    }
}
