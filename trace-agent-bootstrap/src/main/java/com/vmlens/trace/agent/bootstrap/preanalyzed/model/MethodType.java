package com.vmlens.trace.agent.bootstrap.preanalyzed.model;


import java.io.DataOutputStream;
import java.io.IOException;

public interface MethodType {


    void add(MethodTypeContext context);

    void serialize(DataOutputStream out) throws IOException;
}
