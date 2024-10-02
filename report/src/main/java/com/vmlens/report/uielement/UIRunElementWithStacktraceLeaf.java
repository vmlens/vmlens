package com.vmlens.report.uielement;

public class UIRunElementWithStacktraceLeaf {

    private final UIRunElement runElement;
    private final UIStacktraceLeaf stacktraceLeaf;

    public UIRunElementWithStacktraceLeaf(UIRunElement runElement, UIStacktraceLeaf stacktraceLeaf) {
        this.runElement = runElement;
        this.stacktraceLeaf = stacktraceLeaf;
    }

    public UIRunElement runElement() {
        return runElement;
    }

    public UIStacktraceLeaf stacktraceLeaf() {
        return stacktraceLeaf;
    }
}
