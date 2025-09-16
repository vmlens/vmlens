package com.vmlens.report.runelementtype;

public enum UIThreadOperation {
    START("Start") , JOIN("Join");


    private final String text;

    UIThreadOperation(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
