package com.anarsoft.trace.agent.preanalyzed.model;

import java.io.DataOutputStream;
import java.io.IOException;

public class PackageOrClass {

    private final String name;
    private final ClassType classType;
    private final PreAnalyzedMethod[] methods;

    public PackageOrClass(String name,
                          ClassType classType,
                          PreAnalyzedMethod[] methods) {
        this.name = name;
        this.classType = classType;
        this.methods = methods;
    }

    public void serialize(DataOutputStream out) throws IOException {
        out.writeUTF(name);
        classType.serialize(out);
        out.writeInt(methods.length);
        for (PreAnalyzedMethod method : methods) {
            method.serialize(out);
        }
    }
}
