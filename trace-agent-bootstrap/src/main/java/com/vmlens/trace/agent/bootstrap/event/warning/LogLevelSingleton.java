package com.vmlens.trace.agent.bootstrap.event.warning;

import static com.vmlens.trace.agent.bootstrap.event.warning.LogLevel.INFO;

public class LogLevelSingleton {

    private static volatile LogLevel logLevel = INFO;

    public static LogLevel logLevel() {
        return logLevel;
    }

    public static void setLogLevel(LogLevel logLevel) {
        LogLevelSingleton.logLevel = logLevel;
    }
}
