package com.vmlens.report.runelementtype;

public enum LockOperation {

    LOCK_ENTER("enter"), 
    LOCK_EXIT("exit") , 
    CONDITION_WAIT_ENTER("wait enter"),
    CONDITION_WAIT_EXIT("wait exit");

    private final String text;

    LockOperation(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
