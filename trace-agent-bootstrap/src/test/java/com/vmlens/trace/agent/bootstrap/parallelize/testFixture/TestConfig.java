package com.vmlens.trace.agent.bootstrap.parallelize.testFixture;

import org.apache.commons.collections4.bag.HashBag;

public class TestConfig {

    public final SyncActionModel[] givenRun;

    public final ExpectedRun[] expectedRuns;

    public final int numberofRuns;


    public TestConfig(SyncActionModel[] givenRun, ExpectedRun[] expectedRuns, int numberofRuns) {
        this.givenRun = givenRun;
        this.expectedRuns = expectedRuns;
        this.numberofRuns = numberofRuns;
    }

    public static TestConfig config(SyncActionModel[] givenRun, ExpectedRun[] expectedRuns, int numberofRuns) {
        return new TestConfig(givenRun, expectedRuns, numberofRuns);
    }

    public static SyncActionModel[] given(SyncActionModel... givenRun) {
        return givenRun;
    }

    public static ExpectedRun[] expected(ExpectedRun... expected) {
        return expected;
    }


    public HashBag<Integer> threadIndices() {
        HashBag<Integer> result = new HashBag<>();
        ExpectedRun run = expectedRuns[0];
        for (Integer id : run.threadIndices) {
            result.add(id);
        }
        return result;
    }

}
