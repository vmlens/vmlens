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

    public Long[] toThreadIds(ThreadIndexToThreadId threadIndexToThreadId) {
        Long[] result = new Long[threadIndices.length];
        int i = 0;
        for (int threadIndex : threadIndices) {
            result[i] = threadIndexToThreadId.threadId(threadIndex);
            i++;
        }
        return result;
    }

}