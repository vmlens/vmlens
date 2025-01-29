package com.anarsoft.trace.agent.preanalyzed.model;


import com.anarsoft.trace.agent.preanalyzed.builder.ClassBuilder;

import java.io.DataOutputStream;

public interface ClassType {

    void add(String name, PreAnalyzedMethod[] methods, ClassBuilder classBuilder);

    void serialize(ClassTypeCollection classTypeCollection, DataOutputStream out);
}
