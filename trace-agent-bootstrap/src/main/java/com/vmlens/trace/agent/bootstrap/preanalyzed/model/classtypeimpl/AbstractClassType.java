package com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.model.ClassType;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.ClassTypeCollection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class AbstractClassType implements ClassType {

    public static ClassType deserialize(DataInputStream inputStream) throws IOException {
        ClassTypeCollection classTypeCollection =
                new ClassTypeCollection();
        int id = inputStream.readInt();
        return classTypeCollection.type(id);
    }

    @Override
    public void serialize(DataOutputStream out) throws IOException {
        ClassTypeCollection classTypeCollection =
                new ClassTypeCollection();
        out.writeInt(classTypeCollection.id(this));
    }
}
