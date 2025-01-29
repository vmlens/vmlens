package com.anarsoft.trace.agent.preanalyzed.builder;

public interface ClassBuilder {

    MethodBuilder createThread(String name);

    void createTraceAnonymousClass(String name);

    void createFilter(String name);

}
