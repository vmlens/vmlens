package com.vmlens.report.element;

public class TestResult {
    
    private final String text;
    private final String style;

    public TestResult(String text, String style) {
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
