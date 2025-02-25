package com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.model.ClassType;
import com.anarsoft.trace.agent.preanalyzed.model.ClassTypeCollection;

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
