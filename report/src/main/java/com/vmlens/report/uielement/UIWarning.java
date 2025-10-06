package com.vmlens.report.uielement;

public class UIWarning implements UITestLoopOrWarning {

    private final String text;

    public UIWarning(String text) {
        this.text = text;
    }

    public boolean isWarning() {
        return true;
    }

    public String text() {
        return text;
    }
}
