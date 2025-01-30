package com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.MethodBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.MethodType;

public class MethodTypeThreadStart extends AbstractMethodType {

    public static final MethodType SINGLETON = new MethodTypeThreadStart();

    @Override
    public void add(String name, String desc, MethodBuilder methodBuilder) {

    }
}
