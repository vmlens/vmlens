package com.vmlens.trace.agent.bootstrap;

public enum LoggerLevel {
    DEBUG(true, true), INFO(false, true), ERROR(false, false);

    public final boolean logDebug;
    public final boolean logInfo;

    LoggerLevel(boolean logDebug, boolean logInfo) {
        this.logDebug = logDebug;
        this.logInfo = logInfo;
    }


}
