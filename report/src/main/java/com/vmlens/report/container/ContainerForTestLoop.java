package com.vmlens.report.container;

import com.vmlens.trace.agent.bootstrap.description.TestLoopDescription;

public class ContainerForTestLoop implements Container {

    private final TestLoopDescription testLoopDescription;

    public ContainerForTestLoop(TestLoopDescription testLoopDescription) {
        this.testLoopDescription = testLoopDescription;
    }

    @Override
    public String getName() {
        return testLoopDescription.name();
    }
}
