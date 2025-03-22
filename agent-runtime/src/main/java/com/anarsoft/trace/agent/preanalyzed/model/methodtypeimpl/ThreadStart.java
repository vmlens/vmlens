package com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.MethodBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.MethodType;

public class ThreadStart extends AbstractMethodType {

    public static final MethodType SINGLETON = new ThreadStart();

    @Override
    public void add(String name, String desc, MethodBuilder methodBuilder) {
        methodBuilder.addThreadStart(name, desc);
    }
}
