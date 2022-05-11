package com.vmlens.trace.agent.bootstrap.parallelize.testFixture;

public class TestConfig {

    public final ExpectedRun expectedRun;

    public final GivenRun givenRun;


    public TestConfig(ExpectedRun expectedRun, GivenRun givenRun) {
        this.expectedRun = expectedRun;
        this.givenRun = givenRun;
    }

    public static TestConfig config(ExpectedRun expectedRun, GivenRun givenRun) {
        return new TestConfig(expectedRun, givenRun);
    }

}
