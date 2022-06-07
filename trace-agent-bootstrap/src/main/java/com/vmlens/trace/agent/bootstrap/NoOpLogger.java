package com.vmlens.trace.agent.bootstrap;

public class NoOpLogger extends Logger {

    public NoOpLogger() {
        super(LoggerConfig.errorConfig());
    }

    @Override
    protected void log(Class inClass, String message) {

    }
}
