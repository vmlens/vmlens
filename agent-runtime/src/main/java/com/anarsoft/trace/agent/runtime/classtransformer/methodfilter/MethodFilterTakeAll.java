package com.anarsoft.trace.agent.runtime.classtransformer.methodfilter;

public class MethodFilterTakeAll implements MethodFilter {

    @Override
    public boolean take(String name, String desc) {
        return true;
    }
}
