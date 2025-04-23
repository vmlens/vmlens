package com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.model.MethodType;
import com.anarsoft.trace.agent.preanalyzed.model.MethodTypeCollection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class AbstractMethodType implements MethodType {

    public static MethodType deserialize(DataInputStream inputStream) throws IOException {
        MethodTypeCollection methodTypeCollection =
                new MethodTypeCollection();
        int id = inputStream.readInt();
        return methodTypeCollection.type(id);
    }

    @Override
    public void serialize(DataOutputStream out) throws IOException {
        MethodTypeCollection methodTypeCollection =
                new MethodTypeCollection();
        out.writeInt(methodTypeCollection.id(this));
    }
}
