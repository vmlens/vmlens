package com.vmlens.report.element;

import com.anarsoft.trace.agent.description.TestLoopDescription;

public class TestLoopContainer implements Container {

    private final TestLoopDescription testLoopDescription;

    public TestLoopContainer(TestLoopDescription testLoopDescription) {
        this.testLoopDescription = testLoopDescription;
    }

    @Override
    public String getName() {
        return testLoopDescription.name();
    }
}
