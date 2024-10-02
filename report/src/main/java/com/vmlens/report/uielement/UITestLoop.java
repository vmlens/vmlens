package com.vmlens.report.uielement;

public class UITestLoop {


    private final int index;
    private final String name;
    private final int count;
    private final String imagePath;
    private final String resultText;
    private String link;

    public UITestLoop(int index, String name, int count, String imagePath, String resultText) {
        this.index = index;
        this.name = name;
        this.count = count;
        this.imagePath = imagePath;
        this.resultText = resultText;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
