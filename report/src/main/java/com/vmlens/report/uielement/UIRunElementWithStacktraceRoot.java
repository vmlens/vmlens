package com.vmlens.report.uielement;

public class UIRunElementWithStacktraceRoot {

    private final UIRunElement runElement;
    private final UIStacktraceRoot stacktraceRoot;

    public UIRunElementWithStacktraceRoot(UIRunElement runElement, UIStacktraceRoot stacktraceRoot) {
        this.runElement = runElement;
        this.stacktraceRoot = stacktraceRoot;
    }

    public UIRunElement runElement() {
        return runElement;
    }

    public UIStacktraceRoot stacktraceRoot() {
        return stacktraceRoot;
    }
}
