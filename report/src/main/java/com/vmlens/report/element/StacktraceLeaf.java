package com.vmlens.report.element;

import java.util.List;

public class StacktraceLeaf {

    private final List<StacktraceElement> stacktraceElements;

    public StacktraceLeaf(List<StacktraceElement> stacktraceElements) {
        this.stacktraceElements = stacktraceElements;
    }

    public List<StacktraceElement> stacktraceElements() {
        return stacktraceElements;
    }
}
