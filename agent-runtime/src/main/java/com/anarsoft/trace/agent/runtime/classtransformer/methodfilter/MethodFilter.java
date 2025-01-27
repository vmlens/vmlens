package com.anarsoft.trace.agent.runtime.classtransformer.methodfilter;

public interface MethodFilter {

    boolean take(String name, String desc);

}
