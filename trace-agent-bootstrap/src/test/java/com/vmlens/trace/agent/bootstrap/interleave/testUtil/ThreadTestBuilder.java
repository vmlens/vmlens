package com.vmlens.trace.agent.bootstrap.interleave.testUtil;

import com.vmlens.trace.agent.bootstrap.testfixture.ResultTestBuilder;

public class ThreadTestBuilder {
    private final ResultTestBuilder resultTestBuilder;
    private final int threadIndex;
    private int position;

    public ThreadTestBuilder(ResultTestBuilder resultTestBuilder, int threadIndex) {
        this.resultTestBuilder = resultTestBuilder;
        this.threadIndex = threadIndex;
    }


}
