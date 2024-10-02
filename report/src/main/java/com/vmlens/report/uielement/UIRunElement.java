package com.vmlens.report.uielement;

public class UIRunElement {

    private final String operation;
    private final String method;
    private final String threadId;

    private boolean hasLink;
    private String link;

    public UIRunElement(String operation, String method, String threadId) {
        this.operation = operation;
        this.method = method;
        this.threadId = threadId;
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

    public String threadId() {
        return threadId;
    }

    public boolean hasLink() {
        return hasLink;
    }

    public String link() {
        return link;
    }
}
