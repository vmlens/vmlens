package com.vmlens.report.assertion;

public class DebugLoggerToSystemOut implements DebugLogger {

    @Override
    public void println(String message) {
        System.out.println(message);
    }
}
