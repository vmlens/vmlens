package com.vmlens.report.operationtextfactory;

public enum MonitorOperation {

    MONITOR_ENTER("monitor enter"), MONITOR_EXIT("monitor exit");

    private final String text;

    MonitorOperation(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
