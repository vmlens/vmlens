package com.vmlens.report.overview;

public class UITestLoop implements UITestLoopOrWarning  {

    private final String name;
    private final int count;
    private final String resultText;
    private int index;
    private String link;
    private String dominatorTreeLink;

    public UITestLoop(String name, int count, String resultText) {
        this.name = name;
        this.count = count;
        this.resultText = resultText;
    }

    public void setLinks(String runLink, String dominatorTreeLink) {
        this.link = runLink;
        this.dominatorTreeLink = dominatorTreeLink;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String name() {
        return name;
    }

    public int count() {
        return count;
    }

    public String resultText() {
        return resultText;
    }

    public int index() {
        return index;
    }

    public String link() {
        return link;
    }

    public boolean isWarning() {
        return false;
    }

    public String dominatorTreeLink() {
        return dominatorTreeLink;
    }
}
