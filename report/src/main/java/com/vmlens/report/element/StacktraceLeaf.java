package com.vmlens.report.element;

import java.util.List;

public class StacktraceLeaf {

    private final int methodId;
    private final List<StacktraceElement> stacktraceElements;

    public StacktraceLeaf(int methodId, List<StacktraceElement> stacktraceElements) {
        this.methodId = methodId;
        this.stacktraceElements = stacktraceElements;
    }
}
