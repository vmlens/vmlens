package com.vmlens.report.runelementtype;

public enum ReportLockType {

    MONITOR("monitor","monitor") ,
    REENTRANT_LOCK("reentrant lock", "write") ,
    READ_LOCK("read lock" , "read") ,
    WRITE_LOCK("write lock" , "write");

    private final String text;
    private final String textForMethodWithLock;

    ReportLockType(String text, String textForMethodWithLock) {
        this.text = text;
        this.textForMethodWithLock = textForMethodWithLock;
    }

    public String text() {
        return text;
    }

    public String textForMethodWithLock() {
        return textForMethodWithLock;
    }
}
