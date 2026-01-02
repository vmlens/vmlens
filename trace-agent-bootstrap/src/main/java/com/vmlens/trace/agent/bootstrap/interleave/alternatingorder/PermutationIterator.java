package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

/**
 * hides the algorithm to calculate all binary permutations for a given length
 */
public class PermutationIterator {

    private long index;
    private long maxIndex;
    private int length;

    public PermutationIterator(int length) {
        this.maxIndex = calculateMaxIndex(length);
        this.length = length;
    }

    public Permutation next() {
        long temp = index;
        index++;
        return new Permutation(temp);
    }

    public boolean hasNext() {
        return index < maxIndex;
    }

    public int length() {
        return length;
    }

    public void setNewLength(int newLength) {
        length = newLength;
        maxIndex = calculateMaxIndex(newLength);;
    }

    private static long calculateMaxIndex(int length) {
        if(length == 0) {
           return  0;
        } else {
            return  (long) Math.pow(2, length);
        }
    }

}
