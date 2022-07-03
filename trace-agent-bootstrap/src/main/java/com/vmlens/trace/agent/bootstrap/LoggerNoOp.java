package com.vmlens.trace.agent.bootstrap;

public class LoggerNoOp extends Logger {

    public LoggerNoOp() {
        super(LoggerConfig.errorConfig());
    }

    @Override
    protected void log(String module, Class inClass, String message) {

    }
}
