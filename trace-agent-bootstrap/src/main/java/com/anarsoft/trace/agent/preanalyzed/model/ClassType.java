package com.anarsoft.trace.agent.preanalyzed.model;


import com.anarsoft.trace.agent.preanalyzed.builder.ClassTransformerListBuilder;

import java.io.DataOutputStream;
import java.io.IOException;

public interface ClassType {

    void addToBuilder(String name, PreAnalyzedMethod[] methods, ClassTransformerListBuilder classBuilder);

    void serialize(DataOutputStream out) throws IOException;
}
