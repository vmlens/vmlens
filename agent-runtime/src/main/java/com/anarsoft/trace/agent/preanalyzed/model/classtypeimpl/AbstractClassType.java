package com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.model.ClassType;
import com.anarsoft.trace.agent.preanalyzed.model.ClassTypeCollectionBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.TypeCollection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class AbstractClassType implements ClassType {

    public static ClassType deserialize(DataInputStream inputStream) throws IOException {
        TypeCollection<ClassType> classTypeCollection =
                new ClassTypeCollectionBuilder().build();
        int id = inputStream.readInt();
        return classTypeCollection.type(id);
    }

    @Override
    public void serialize(DataOutputStream out) throws IOException {
        TypeCollection<ClassType> classTypeCollection =
                new ClassTypeCollectionBuilder().build();
        out.writeInt(classTypeCollection.id(this));
    }
}
