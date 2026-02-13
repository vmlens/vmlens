package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * bases are the number of alternatives
 * base 2 results in 0,1
 */

public class MixedRadixIterator implements Iterator<int[]> {

    private final int[] bases;
    private final int[] current;
    private boolean hasNext = true;

    public MixedRadixIterator(int[] bases) {
        this.bases = bases;
        this.current = new int[bases.length];

        // Edge case: empty or invalid bases
        if (bases.length == 0) {
            hasNext = false;
        }
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public int[] next() {
        if (!hasNext) {
            throw new NoSuchElementException();
        }

        // Copy current value to return
        int[] result = Arrays.copyOf(current, current.length);

        // Increment for next call
        increment();
        return result;
    }

    private void increment() {
        int i = bases.length - 1;

        while (i >= 0) {
            current[i]++;
            if (current[i] < bases[i]) {
                return;
            }
            current[i] = 0;
            i--;
        }

        // overflow → no more elements
        hasNext = false;
    }

}
