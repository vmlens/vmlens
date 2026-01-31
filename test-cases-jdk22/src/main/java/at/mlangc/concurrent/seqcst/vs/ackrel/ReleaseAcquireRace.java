package at.mlangc.concurrent.seqcst.vs.ackrel;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.System.out;

class ReleaseAcquireRace {
    final AtomicBoolean started1 = new AtomicBoolean();
    final AtomicBoolean started2 = new AtomicBoolean();

    boolean first1;
    boolean first2;

    void run1() {
        started1.set(true);

        if (!started2.get()) {
            first1 = true;
        }
    }

    void run2() {
        started2.set(true);

        if (!started1.get()) {
            first2 = true;
        }
    }

    public static void main(String[] args) {
        for (long tries = 1; tries < Long.MAX_VALUE; tries++) {
            var race = new ReleaseAcquireRace();

            var run1 = CompletableFuture.runAsync(race::run1);
            var run2 = CompletableFuture.runAsync(race::run2);

            run1.join();
            run2.join();

            if (race.first1 && race.first2) {
                out.printf("Both threads won after %s tries%n", tries);
                break;
            }

            if (tries % (1 << 20) == 0) {
                out.printf("Never saw both threads winning after %s tries%n", tries);
            }
        }
    }
}
