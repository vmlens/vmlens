package com.vmlens.report.output;

public class UIRunElementWithStacktraceLeaf {

    private final UIRunElement runElement;
    private final UIStacktraceLeaf stacktraceLeaf;

    public UIRunElementWithStacktraceLeaf(UIRunElement runElement, UIStacktraceLeaf stacktraceLeaf) {
        this.runElement = runElement;
        this.stacktraceLeaf = stacktraceLeaf;
    }

    public UIRunElement createRunElement() {
        if (stacktraceLeaf.reportLink() != null) {
            runElement.setLink(stacktraceLeaf().reportLink());
        }
        return runElement;
    }

    public UIStacktraceLeaf stacktraceLeaf() {
        return stacktraceLeaf;
    }
}
