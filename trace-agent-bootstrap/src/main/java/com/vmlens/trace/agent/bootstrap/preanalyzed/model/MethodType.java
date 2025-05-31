package com.vmlens.trace.agent.bootstrap.preanalyzed.model;


import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;

import java.io.DataOutputStream;
import java.io.IOException;

public interface MethodType {


    void add(String name,
             String desc,
             FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder);

    void serialize(DataOutputStream out) throws IOException;
}
