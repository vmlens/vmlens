package com.vmlens.report.element;

import java.util.List;

public class StacktraceRoot {

    private final int methodId;
    private final List<StacktraceElement> stacktraceElements;

    public StacktraceRoot(int methodId, List<StacktraceElement> stacktraceElements) {
        this.methodId = methodId;
        this.stacktraceElements = stacktraceElements;
    }
}
