package at.mlangc.concurrent.seqcst.vs.ackrel;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

class ReentrantGetAndSetLock extends IndexedLock {
    private static final VarHandle LOCKED;

    static {
        try {
            LOCKED = MethodHandles.lookup().findVarHandle(ReentrantGetAndSetLock.class, "locked", long.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private final MemoryOrdering memoryOrdering;
    private long locked = -1;

    ReentrantGetAndSetLock() {
        this(MemoryOrdering.ACQUIRE_RELEASE);
    }

    ReentrantGetAndSetLock(MemoryOrdering memoryOrdering) {
        this.memoryOrdering = memoryOrdering;
    }

    @Override
    int threadLimit() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void lock() {
        while (!tryLock()) {
            Thread.onSpinWait();
        }
    }

    @Override
    public void unlock() {
        var threadId = Thread.currentThread().getId();

        switch (memoryOrdering) {
            case PLAIN -> locked = -1;
            case ACQUIRE_RELEASE -> LOCKED.setRelease(this, -1);
            case VOLATILE -> LOCKED.setVolatile(this, -1);
        }
    }

    private boolean tryLock() {
        var threadId = Thread.currentThread().getId();

        return switch (memoryOrdering) {
            case VOLATILE -> {
                var previousOwner = (long) LOCKED.compareAndExchange(this, -1, threadId);
                yield  previousOwner < 0 || previousOwner == threadId;
            }

            case ACQUIRE_RELEASE -> {
                var previousOwner = (long) LOCKED.compareAndExchangeAcquire(this, -1, threadId);
                yield  previousOwner < 0 || previousOwner == threadId;
            }

            case PLAIN -> {
                if (locked == threadId) {
                    yield true;
                }

                yield LOCKED.weakCompareAndSetPlain(this, -1, threadId);
            }
        };
    }
}
