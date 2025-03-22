package com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.MethodBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.MethodType;

public class ThreadJoin extends AbstractMethodType {

    public static final MethodType SINGLETON = new ThreadJoin();

    @Override
    public void add(String name, String desc, MethodBuilder methodBuilder) {
        methodBuilder.addThreadJoin(name, desc);
    }


}
