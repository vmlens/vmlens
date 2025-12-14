package com.vmlens.report.input.run;

public enum ReportLockType {

    MONITOR("Monitor","Monitor") ,
    REENTRANT_LOCK("ReentrantLock", "WriteLock") ,
    READ_LOCK("ReadLock" , "AtomicRead") ,
    WRITE_LOCK("WriteLock" , "WriteLock");

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
