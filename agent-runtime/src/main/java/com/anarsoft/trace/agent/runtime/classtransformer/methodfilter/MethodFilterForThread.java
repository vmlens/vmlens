package com.anarsoft.trace.agent.runtime.classtransformer.methodfilter;

public class MethodFilterForThread implements MethodFilter {
    @Override
    public boolean take(String name, String desc) {
        if (name.equals("start")) {
            return true;
        }
        if (name.equals("join")) {
            return true;
        }

        return false;
    }
}
