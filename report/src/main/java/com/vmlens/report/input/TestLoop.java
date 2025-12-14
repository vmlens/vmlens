package com.vmlens.report.input;

import com.vmlens.report.description.NeedsDescriptionCallback;

public class TestLoop {


    private final int loopId;
    private final TestResult testResult;
    private final int count;


    public TestLoop(int loopId, TestResult testResult, int count) {
        this.loopId = loopId;
        this.testResult = testResult;
        this.count = count;
    }

    public int loopId() {
        return loopId;
    }

    public TestResult testResult() {
        return testResult;
    }

    public int count() {
        return count;
    }

    public void addToNeedsDescription(NeedsDescriptionCallback callback) {
        testResult.addToNeedsDescription(callback);
    }
}
