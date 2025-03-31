package com.vmlens.report.runelementtype;

public enum LockOperation {

    LOCK_ENTER("enter"), LOCK_EXIT("exit");

    private final String text;

    LockOperation(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
