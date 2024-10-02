package com.vmlens.report.uielement;

public class UITestLoop {



    private final String name;
    private final int count;
    private final String resultText;
    private final String resultStyle;

    private int index;
    private String link;

    public UITestLoop(String name, int count, String resultText,
                      String resultStyle) {
        this.name = name;
        this.count = count;
        this.resultText = resultText;
        this.resultStyle = resultStyle;
    }

    public void setLink(String link) {
        this.link = link;
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

    public String resultStyle() {
        return resultStyle;
    }

    public int index() {
        return index;
    }

    public String link() {
        return link;
    }

}
