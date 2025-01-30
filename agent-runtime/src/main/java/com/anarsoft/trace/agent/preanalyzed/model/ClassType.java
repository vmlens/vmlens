package com.anarsoft.trace.agent.preanalyzed.model;


import com.anarsoft.trace.agent.preanalyzed.builder.ClassBuilder;

import java.io.DataOutputStream;
import java.io.IOException;

public interface ClassType {

    void add(String name, PreAnalyzedMethod[] methods, ClassBuilder classBuilder);

    void serialize(DataOutputStream out) throws IOException;
}
