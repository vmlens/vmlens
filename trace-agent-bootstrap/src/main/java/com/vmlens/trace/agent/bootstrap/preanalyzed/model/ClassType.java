package com.vmlens.trace.agent.bootstrap.preanalyzed.model;


import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.ClassTransformerListBuilder;

import java.io.DataOutputStream;
import java.io.IOException;

public interface ClassType {

    void addToBuilder(String name, PreAnalyzedMethod[] methods, ClassTransformerListBuilder classBuilder);

    void serialize(DataOutputStream out) throws IOException;
}
