package com.vmlens.report.uielement;

public class UIRunElement {

    private final int positionInRun;
    private final String operation;
    private final String onElement;
    private final String method;
    private final String threadName;
    private final boolean isSeparator;

    private boolean hasLink;
    private String link;

    public static UIRunElement createNewRun(int runId) {
        return new UIRunElement(-1,"New Run", "" , "" , "" , true);
    }

    public UIRunElement(int positionInRun,
                        String operation,
                        String onElement,
                        String method,
                        String threadName,
                        boolean isSeparator) {
        this.positionInRun = positionInRun;
        this.operation = operation;
        this.onElement = onElement;
        this.method = method;
        this.threadName = threadName;
        this.isSeparator = isSeparator;
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

    public String onElement() {
        return onElement;
    }
}
