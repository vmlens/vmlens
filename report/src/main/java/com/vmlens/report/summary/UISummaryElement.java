package com.vmlens.report.summary;

public class UISummaryElement {

    private final String operation;
    private final String element;
    private final String object;
    private final String method;
    private final String threadName;
    private final int count;
    private final boolean hasLink;
    private final String link;

    public UISummaryElement(String operation,
                            String element,
                            String object,
                            String method,
                            String threadName,
                            int count,
                            boolean hasLink,
                            String link) {
        this.operation = operation;
        this.element = element;
        this.object = object;
        this.method = method;
        this.threadName = threadName;
        this.count = count;
        this.hasLink = hasLink;
        this.link = link;
    }

    public String operation() {
        return operation;
    }

    public String element() {
        return element;
    }

    public String object() {
        return object;
    }

    public String method() {
        return method;
    }

    public String threadName() {
        return threadName;
    }

    public int count() {
        return count;
    }

    public boolean hasLink() {
        return hasLink;
    }

    public String link() {
        return link;
    }
}
