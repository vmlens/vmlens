package com.vmlens.trace.agent.bootstrap;

public class LoggerConfig {

    public final LoggerLevel interleave;

    private LoggerConfig(LoggerLevel interleave) {
        this.interleave = interleave;
    }

    public static LoggerConfig debugConfig() {
        return new LoggerConfig(LoggerLevel.DEBUG);
    }

    public static LoggerConfig infoConfig() {
        return new LoggerConfig(LoggerLevel.INFO);
    }

    public static LoggerConfig errorConfig() {
        return new LoggerConfig(LoggerLevel.ERROR);
    }
}
