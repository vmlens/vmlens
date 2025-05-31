package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.model.MethodType;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.MethodTypeCollection;

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
