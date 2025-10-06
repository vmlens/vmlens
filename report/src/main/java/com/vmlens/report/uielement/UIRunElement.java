package com.vmlens.report.uielement;

import java.util.Objects;

public class UIRunElement {

    private final int positionInRun;
    private final String operation;
    private final String element;
    private final String object;
    private final String method;
    private final String threadName;
    private final boolean isSeparator;

    private boolean hasLink;
    private String link;

    public static UIRunElement createNewRun(int runId) {
        return new UIRunElement(-1,"New Run", "" , "" , "","" , true);
    }

    public UIRunElement(int positionInRun,
                        String operation,
                        String element,
                        String object,
                        String method,
                        String threadName,
                        boolean isSeparator) {
        this.positionInRun = positionInRun;
        this.operation = operation;
        this.element = element;
        this.object = object;
        this.method = method;
        this.threadName = threadName;
        this.isSeparator = isSeparator;
    }

    public UISummaryElement toSummary(int count) {
        return new UISummaryElement( operation,
                 element,
                 object,
                 method,
                 threadName,
                 count,
                 hasLink,
                 link);
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

    public boolean isSeparator() {
        return isSeparator;
    }

    public int positionInRun() {
        return positionInRun;
    }

    public String element() {
        return element;
    }

    public String object() {
        return object;
    }

    @Override
    public boolean equals(Object object1) {
        if (object1 == null || getClass() != object1.getClass()) return false;
        UIRunElement that = (UIRunElement) object1;
        return Objects.equals(operation, that.operation) && Objects.equals(element, that.element) && Objects.equals(object, that.object) && Objects.equals(method, that.method) && Objects.equals(threadName, that.threadName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, element, object, method, threadName);
    }
}
