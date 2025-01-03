package com.vmlens.api;

public enum LogLevel {

    DEBUG(true, true),
    INFO(false, true),
    ERROR(false, false);

    private final boolean isDebugEnabled;
    private final boolean isInfoEnabled;

    LogLevel(boolean isDebugEnabled, boolean isInfoEnabled) {
        this.isDebugEnabled = isDebugEnabled;
        this.isInfoEnabled = isInfoEnabled;
    }

    public boolean isDebugEnabled() {
        return isDebugEnabled;
    }

    public boolean isInfoEnabled() {
        return isInfoEnabled;
    }
}
