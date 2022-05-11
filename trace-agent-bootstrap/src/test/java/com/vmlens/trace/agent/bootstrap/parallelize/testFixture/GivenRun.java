package com.vmlens.trace.agent.bootstrap.parallelize.testFixture;

public class GivenRun {

    public final ThreadModel[] threadModelArray;

    public GivenRun(ThreadModel[] threadModelArray) {
        this.threadModelArray = threadModelArray;
    }

    public static GivenRun given(ThreadModel... threadModels) {
        return new GivenRun(threadModels);
    }


}
