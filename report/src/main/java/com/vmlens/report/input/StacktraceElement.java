package com.vmlens.report.input;

public class StacktraceElement {

    private final int methodId;

    public StacktraceElement(int methodId) {
        this.methodId = methodId;
    }

    public int methodId() {
        return methodId;
    }
}
