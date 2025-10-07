package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

public class Permutation {

    private final long index;

    public Permutation(long index) {
        this.index = index;
    }

    public boolean at(int position) {
        return (index & (1L << position)) != 0;
    }
}
