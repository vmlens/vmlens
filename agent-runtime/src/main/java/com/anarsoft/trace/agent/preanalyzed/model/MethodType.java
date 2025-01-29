package com.anarsoft.trace.agent.preanalyzed.model;


import com.anarsoft.trace.agent.preanalyzed.builder.MethodBuilder;

import java.io.DataOutputStream;

public interface MethodType {

    void add(String name, String desc, MethodBuilder methodBuilder);

    void serialize(DataOutputStream out);
}
