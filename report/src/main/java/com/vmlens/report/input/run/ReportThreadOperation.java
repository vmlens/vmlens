package com.vmlens.report.input.run;

public enum ReportThreadOperation {
    START("Start") , JOIN("Join");


    private final String text;

    ReportThreadOperation(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
