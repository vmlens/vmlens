package com.vmlens.trace.agent.bootstrap.parallelize.functionalTest.util;

import com.vmlens.trace.agent.bootstrap.Logger;
import com.vmlens.trace.agent.bootstrap.LoggerConfig;

public class TestDebugLogger extends Logger {

    public TestDebugLogger() {
        super(LoggerConfig.infoConfig());
    }

    @Override
    protected void log(Class inClass, String message) {
        System.out.println(inClass.getSimpleName() + ": " + message);
    }
}
