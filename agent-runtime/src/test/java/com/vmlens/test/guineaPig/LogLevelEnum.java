package com.vmlens.test.guineaPig;

public enum LogLevelEnum {

    DEBUG(true, true),
    INFO(false, true),
    ERROR(false, false);

    private final boolean isDebugEnabled;
    private final boolean isInfoEnabled;

    LogLevelEnum(boolean isDebugEnabled, boolean isInfoEnabled) {
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
