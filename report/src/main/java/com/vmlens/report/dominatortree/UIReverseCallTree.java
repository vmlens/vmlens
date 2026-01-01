package com.vmlens.report.dominatortree;

public class UIReverseCallTree {

    private final String label;
    private final String css;

    public UIReverseCallTree(String label, String css) {
        this.label = label;
        this.css = css;
    }

    public String label() {
        return label;
    }

    public String css() {
        return css;
    }
}
