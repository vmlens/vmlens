package at.mlangc.concurrent.seqcst.vs.ackrel;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class ShowDifferenceBetweenVolatileGetSetAndGetAcquireSetRelease {
    private volatile boolean stop = false;
    private final LongAdder tries = new LongAdder();

    public static void main(String[] args) {
        new ShowDifferenceBetweenVolatileGetSetAndGetAcquireSetRelease().run();
    }

    void run() {
        while (!stop) {
            new StoreReleaseDemo(
                    AtomicBoolean::getAcquire,
                    AtomicBoolean::setRelease
            ).run();
        }
    }

    interface AtomicSetter {
        void set(AtomicBoolean atomic, boolean b);
    }

    interface AtomicGetter {
        boolean get(AtomicBoolean atomic);
    }

    class StoreReleaseDemo {
        final AtomicBoolean a = new AtomicBoolean();
        final AtomicBoolean b = new AtomicBoolean();
        final AtomicInteger counter = new AtomicInteger();

        final AtomicSetter atomicSetter;
        final AtomicGetter atomicGetter;

        StoreReleaseDemo(AtomicGetter atomicGetter, AtomicSetter atomicSetter) {
            this.atomicSetter = atomicSetter;
            this.atomicGetter = atomicGetter;
        }

        void taskA() {
            atomicSetter.set(a, true);
            if (!atomicGetter.get(b)) {
                counter.incrementAndGet();
            }
        }

        void taskB() {
            atomicSetter.set(b, true);
            if (!atomicGetter.get(a)) {
                counter.incrementAndGet();
            }
        }

        void run() {
            var ta = CompletableFuture.runAsync(this::taskA);
            var tb = CompletableFuture.runAsync(this::taskB);

            ta.runAfterBoth(tb, () -> {
                if (counter.get() > 1) {
                    System.out.printf("Observed two increments after ~%s tries%n", tries.longValue());
                    stop = true;
                } else {
                    tries.increment();
                }
            });
        }
    }
}
