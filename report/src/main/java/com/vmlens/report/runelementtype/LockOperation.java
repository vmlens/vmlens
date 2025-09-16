package com.vmlens.report.runelementtype;

public enum LockOperation {

    LOCK_ENTER("Enter"),
    LOCK_EXIT("Exit") ,
    CONDITION_WAIT_ENTER("Wait Enter"),
    CONDITION_WAIT_EXIT("Wait Exit");

    private final String text;

    LockOperation(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
