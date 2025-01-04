package com.vmlens.trace.agent.bootstrap.event.warning;

public class LogLevelSingleton {

    private static volatile LogLevel logLevel;

    public static LogLevel logLevel() {
        return logLevel;
    }

    public static void setLogLevel(LogLevel logLevel) {
        LogLevelSingleton.logLevel = logLevel;
    }
}
