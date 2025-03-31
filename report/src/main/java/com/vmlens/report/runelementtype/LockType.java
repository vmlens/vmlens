package com.vmlens.report.runelementtype;

public enum LockType {

    MONITOR("monitor") ;

    private final String text;

    LockType(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
