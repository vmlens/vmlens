package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CallbackInNonBlockingMethod {

    private final  String className;
    private final String methodName;
    private final String desc;
    private final int operation;

    public CallbackInNonBlockingMethod(String className, String methodName, String desc, int operation) {
        this.className = className;
        this.methodName = methodName;
        this.desc = desc;
        this.operation = operation;
    }

    public static CallbackInNonBlockingMethod deserialize(DataInputStream inputStream) throws IOException {
        String className = inputStream.readUTF();
        String methodName = inputStream.readUTF();
        String desc = inputStream.readUTF();
        int operation = inputStream.readInt();
        return new CallbackInNonBlockingMethod( className, methodName, desc, operation);
    }

    public void serialize(DataOutputStream out) throws IOException {
        out.writeUTF(className);
        out.writeUTF(methodName);
        out.writeUTF(desc);
        out.writeInt(operation);
    }
}
