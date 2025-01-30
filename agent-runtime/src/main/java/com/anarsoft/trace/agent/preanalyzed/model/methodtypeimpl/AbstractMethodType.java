package com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.model.MethodType;
import com.anarsoft.trace.agent.preanalyzed.model.MethodTypeCollectionBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.TypeCollection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class AbstractMethodType implements MethodType {

    public static MethodType deserialize(DataInputStream inputStream) throws IOException {
        TypeCollection<MethodType> methodTypeCollection =
                new MethodTypeCollectionBuilder().build();
        int id = inputStream.readInt();
        return methodTypeCollection.type(id);
    }


    @Override
    public void serialize(DataOutputStream out) throws IOException {
        TypeCollection<MethodType> methodTypeCollection =
                new MethodTypeCollectionBuilder().build();
        out.writeInt(methodTypeCollection.id(this));
    }
}
