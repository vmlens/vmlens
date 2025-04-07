package com.anarsoft.trace.agent.preanalyzed.model;


import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.CallbackInNonBlockingMethod;
import com.anarsoft.trace.agent.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;

import java.io.DataOutputStream;
import java.io.IOException;

public interface MethodType {


    void add(String name,
             String desc,
             CallbackInNonBlockingMethod[] callbackInNonBlockingMethods,
             FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder);

    void serialize(DataOutputStream out) throws IOException;
}
