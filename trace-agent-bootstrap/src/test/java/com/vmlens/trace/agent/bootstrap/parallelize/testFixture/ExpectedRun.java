package com.vmlens.trace.agent.bootstrap.parallelize.testFixture;

public class ExpectedRun {

    private final static long[] threadIndexToId = new long[0];

    public final int[] threadIndices;

    public ExpectedRun(int[] threadIndices) {
        this.threadIndices = threadIndices;
    }

    public static ExpectedRun run(int... indices) {
        return new ExpectedRun(indices);
    }


}