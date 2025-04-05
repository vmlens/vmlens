package com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.CallbackInNonBlockingMethod;
import com.anarsoft.trace.agent.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.MethodType;

public class ThreadStart extends AbstractMethodType {

    public static final MethodType SINGLETON = new ThreadStart();

    @Override
    public void add(String name, String desc, CallbackInNonBlockingMethod[] callbackInNonBlockingMethods, FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
        methodBuilder.addThreadStart(name, desc);
    }
}
