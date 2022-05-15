package com.vmlens.trace.agent.bootstrap.parallelize.testFixture;

import java.util.HashSet;
import java.util.Set;

public class TestConfig {

    public final SyncActionModel[] givenRun;

    public final ExpectedRun[] expectedRuns;


    public TestConfig(SyncActionModel[] givenRun, ExpectedRun[] expectedRuns) {
        this.givenRun = givenRun;
        this.expectedRuns = expectedRuns;
    }

    public static TestConfig config(SyncActionModel[] givenRun, ExpectedRun[] expectedRuns) {
        return new TestConfig(givenRun, expectedRuns);
    }

    public static SyncActionModel[] given(SyncActionModel... givenRun) {
        return givenRun;
    }

    public static ExpectedRun[] expected(ExpectedRun... expected) {
        return expected;
    }

    public Set<Long> threadIds(ThreadIndexToThreadId threadIndexToThreadId) {
        Set<Long> result = new HashSet<>();
        for (ExpectedRun run : expectedRuns) {
            for (Long id : run.toThreadIds(threadIndexToThreadId)) {
                result.add(id);
            }
        }
        return result;
    }

}
