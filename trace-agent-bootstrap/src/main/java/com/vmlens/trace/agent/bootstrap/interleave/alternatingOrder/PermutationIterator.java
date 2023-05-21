package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

/**
 * hides the algorithm to calculate all binary permutations for a given length
 */
public class PermutationIterator {

    private long index;
    private final long maxIndex;

    public PermutationIterator(int length) {
        if(length == 0) {
            this.maxIndex = 0;
        } else {
            this.maxIndex = (long) Math.pow(2, length);
        }
    }

    public void advance() {
        index++;
    }

    public boolean at(int position) {
        return (index & (1L << position)) != 0;
    }

    public boolean hasNext() {
        return index < maxIndex;
    }

}
