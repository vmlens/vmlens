package com.anarsoft.trace.agent.preanalyzed.model;

import java.io.DataOutputStream;

public class PreAnalyzedMethod {
    private final String name;
    private final String desc;
    private final MethodType methodType;

    public PreAnalyzedMethod(String name,
                             String desc,
                             MethodType methodType) {
        this.name = name;
        this.desc = desc;
        this.methodType = methodType;
    }


    public void serialize(DataOutputStream out) {
        out.writeUTF(name);
        out.writeUTF(desc);
        methodType.serialize(out);

    }
}
