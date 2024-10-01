package com.vmlens.report.uielement;

import java.util.List;

public class UIStacktraceRoot {

    private final List<UIStacktraceElement> stacktraceElements;
    private String reportLink;

    public UIStacktraceRoot(List<UIStacktraceElement> stacktraceElements) {
        this.stacktraceElements = stacktraceElements;
    }

    public List<UIStacktraceElement> stacktraceElements() {
        return stacktraceElements;
    }

    public String reportLink() {
        return reportLink;
    }

    public void setReportLink(String reportLink) {
        this.reportLink = reportLink;
    }
}
