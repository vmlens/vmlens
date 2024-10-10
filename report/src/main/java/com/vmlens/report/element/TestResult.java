package com.vmlens.report.element;

public enum TestResult {
    SUCCESS("Success", ""), FAILURE("Failure", "style=\"color: red;\""),
    DATA_RACE("Data race", "style=\"color: red;\""),
    FAILURE_AND_DATA_RACE("Failure and data race", "style=\"color: red;\"");


    private final String text;
    private final String style;

    TestResult(String text, String style) {
        this.text = text;
        this.style = style;
    }

    public String text() {
        return text;
    }

    public String style() {
        return style;
    }
}
