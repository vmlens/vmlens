package com.vmlens.report.uielement;

public class UIRunElement {

    private final String operation;
    private final String method;
    private final String threadName;

    private boolean hasLink;
    private String link;

    public UIRunElement(String operation, String method, String threadName) {
        this.operation = operation;
        this.method = method;
        this.threadName = threadName;
    }

    public void setLink(String link) {
        this.link = link;
        this.hasLink = true;
    }

    public String operation() {
        return operation;
    }

    public String method() {
        return method;
    }

    public String threadName() {
        return threadName;
    }

    public boolean hasLink() {
        return hasLink;
    }

    public String link() {
        return link;
    }
}
