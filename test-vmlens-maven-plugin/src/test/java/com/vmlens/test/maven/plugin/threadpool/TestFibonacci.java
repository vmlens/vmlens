package com.vmlens.test.maven.plugin.threadpool;

import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class TestFibonacci implements AutoCloseable {

    private final ConcurrentHashMap<Integer, FibonacciTask> cache;
    private final ForkJoinPool pool;

    public TestFibonacci() {
        cache = new ConcurrentHashMap<>();
        pool = new ForkJoinPool(2);
    }

    @Test
    @Disabled
    public void runFibonacci() {
        try (AllInterleavings allInterleavings = new AllInterleavings("Fibonacci")) {
            while (allInterleavings.hasNext()) {
                try (TestFibonacci fibonacci = new TestFibonacci()) {
                    System.out.println(fibonacci.calculate(9));
                }
            }
        }
    }

    public long calculate(int n) {
        return pool.invoke(new FibonacciTask(n));
    }

    private class FibonacciTask extends RecursiveTask<Long> {

        private final int n;

        private FibonacciTask(int n) {
            this.n = n;
        }

        @Override
        protected Long compute() {
            if (n < 3) {
                return 1L;
            }

            FibonacciTask previousValue = cache.putIfAbsent(n, this);
            if (previousValue != null) {
                long cachedValue = 0;
                try {
                    Long valueA = previousValue.get(10, TimeUnit.SECONDS);
                    if (valueA == null) {
                        System.out.println("not ended");
                        throw new RuntimeException("not ended");
                    }
                    cachedValue += valueA;
                } catch (InterruptedException | ExecutionException | TimeoutException e) {
                    throw new RuntimeException(e);
                }


                // <-- PROBLEMATIC LINE

                return cachedValue;
            }

            FibonacciTask a = new FibonacciTask(n - 2);
            FibonacciTask b = new FibonacciTask(n - 1);
            a.fork();
            b.fork();

            long value = 0L;

            try {
                Long valueA = a.get(10, TimeUnit.SECONDS);
                if (valueA == null) {
                    System.out.println("not ended");
                    throw new RuntimeException("not ended");
                }
                value += valueA;
                Long valueB = b.get(10, TimeUnit.SECONDS);
                if (valueB == null) {
                    System.out.println("not ended");
                    throw new RuntimeException("not ended B");
                }
                value += valueB;
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                throw new RuntimeException(e);
            }
            return value;
        }
    }

    @Override
    public void close() {
        if (pool != null) {
            pool.shutdown();
        }
    }
}
