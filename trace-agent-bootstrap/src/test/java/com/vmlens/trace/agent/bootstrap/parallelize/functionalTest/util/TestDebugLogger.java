package com.vmlens.trace.agent.bootstrap.parallelize.functionalTest.util;

import com.vmlens.trace.agent.bootstrap.Logger;
import com.vmlens.trace.agent.bootstrap.LoggerConfig;

public class TestDebugLogger extends Logger {

    public TestDebugLogger() {
        super(LoggerConfig.infoConfig());
    }

    @Override
    protected void log(String module, Class inClass, String message) {
        System.out.println(module + "(" + inClass.getSimpleName() + "): " + message);
    }
}
