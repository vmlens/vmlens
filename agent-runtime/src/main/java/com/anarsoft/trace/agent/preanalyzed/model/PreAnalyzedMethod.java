package com.anarsoft.trace.agent.preanalyzed.model;

import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.CallbackInNonBlockingMethod;
import com.anarsoft.trace.agent.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.AbstractMethodType;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;


public class PreAnalyzedMethod {
    private final String name;
    private final String desc;
    private final MethodType methodType;
    private final CallbackInNonBlockingMethod[] callbackInNonBlockingMethods;

    public PreAnalyzedMethod(String name,
                             String desc,
                             MethodType methodType,
                             CallbackInNonBlockingMethod[] callbackInNonBlockingMethods) {
        this.name = name;
        this.desc = desc;
        this.methodType = methodType;
        this.callbackInNonBlockingMethods = callbackInNonBlockingMethods;
    }

    public static PreAnalyzedMethod deserialize(DataInputStream inputStream) throws IOException {
        String name = inputStream.readUTF();
        String desc = inputStream.readUTF();
        MethodType methodType = AbstractMethodType.deserialize(inputStream);

        int arrayLength = inputStream.readInt();
        CallbackInNonBlockingMethod[] callbackInNonBlockingMethods = new CallbackInNonBlockingMethod[arrayLength];
        for(int i = 0 ; i < arrayLength; i++) {
            callbackInNonBlockingMethods[i] =  CallbackInNonBlockingMethod.deserialize(inputStream);
        }
        return new PreAnalyzedMethod(name, desc, methodType,new CallbackInNonBlockingMethod[0]);
    }

    public void add(FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
        methodType.add(name, desc, callbackInNonBlockingMethods, methodBuilder);
    }

    public void serialize(DataOutputStream out) throws IOException {
        out.writeUTF(name);
        out.writeUTF(desc);
        methodType.serialize(out);

        out.writeInt(callbackInNonBlockingMethods.length);
        for (CallbackInNonBlockingMethod callbackInNonBlockingMethod : callbackInNonBlockingMethods) {
            callbackInNonBlockingMethod.serialize(out);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PreAnalyzedMethod that = (PreAnalyzedMethod) o;

        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(desc, that.desc)) return false;
        return Objects.equals(methodType, that.methodType);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (methodType != null ? methodType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PreAnalyzedMethod{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", methodType=" + methodType +
                '}';
    }
}
