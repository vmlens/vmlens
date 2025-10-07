package at.mlangc.concurrent.seqcst.vs.ackrel;

import java.util.concurrent.atomic.*;

public enum MemoryOrdering {
    VOLATILE, ACQUIRE_RELEASE, PLAIN;

    int get(AtomicInteger atomic) {
        return switch (this) {
            case PLAIN -> atomic.getPlain();
            case ACQUIRE_RELEASE -> atomic.getAcquire();
            case VOLATILE -> atomic.get();
        };
    }

    void set(AtomicInteger atomic, int value) {
        switch (this) {
            case PLAIN -> atomic.setPlain(value);
            case ACQUIRE_RELEASE -> atomic.setRelease(value);
            case VOLATILE -> atomic.set(value);
        }
    }

    int get(AtomicIntegerArray atomicArray, int index) {
        return switch (this) {
            case PLAIN -> atomicArray.getPlain(index);
            case ACQUIRE_RELEASE -> atomicArray.getAcquire(index);
            case VOLATILE -> atomicArray.get(index);
        };
    }

    void set(AtomicIntegerArray atomicArray, int index, int value) {
        switch (this) {
            case PLAIN -> atomicArray.setPlain(index, value);
            case ACQUIRE_RELEASE -> atomicArray.setRelease(index, value);
            case VOLATILE -> atomicArray.set(index, value);
        }
    }

    long get(AtomicLongArray atomicArray, int index) {
        return switch (this) {
            case PLAIN -> atomicArray.getPlain(index);
            case ACQUIRE_RELEASE -> atomicArray.getAcquire(index);
            case VOLATILE -> atomicArray.get(index);
        };
    }

    void set(AtomicLongArray atomicArray, int index, long value) {
        switch (this) {
            case PLAIN -> atomicArray.setPlain(index, value);
            case ACQUIRE_RELEASE -> atomicArray.setRelease(index, value);
            case VOLATILE -> atomicArray.set(index, value);
        }
    }

    <T> T get(AtomicReferenceArray<T> atomicArray, int index) {
        return switch (this) {
            case PLAIN -> atomicArray.getPlain(index);
            case ACQUIRE_RELEASE -> atomicArray.getAcquire(index);
            case VOLATILE -> atomicArray.get(index);
        };
    }

    <T> void set(AtomicReferenceArray<T> atomicArray, int index, T value) {
        switch (this) {
            case PLAIN -> atomicArray.setPlain(index, value);
            case ACQUIRE_RELEASE -> atomicArray.setRelease(index, value);
            case VOLATILE -> atomicArray.set(index, value);
        }
    }

    boolean get(AtomicBoolean atomic) {
        return switch (this) {
            case PLAIN -> atomic.getPlain();
            case ACQUIRE_RELEASE -> atomic.getAcquire();
            case VOLATILE -> atomic.get();
        };
    }

    void set(AtomicBoolean atomic, boolean value) {
        switch (this) {
            case PLAIN -> atomic.setPlain(value);
            case ACQUIRE_RELEASE -> atomic.setRelease(value);
            case VOLATILE -> atomic.set(value);
        }
    }

    boolean weakCompareAndSet(AtomicInteger atomic, int expectedValue, int newValue) {
        return switch (this) {
            case PLAIN -> atomic.weakCompareAndSetPlain(expectedValue, newValue);
            case ACQUIRE_RELEASE -> atomic.weakCompareAndSetAcquire(expectedValue, newValue);
            case VOLATILE -> atomic.weakCompareAndSetVolatile(expectedValue, newValue);
        };
    }
}
