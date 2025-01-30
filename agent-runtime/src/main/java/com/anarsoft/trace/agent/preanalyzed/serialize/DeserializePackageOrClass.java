package com.anarsoft.trace.agent.preanalyzed.serialize;

import com.anarsoft.trace.agent.preanalyzed.model.PackageOrClass;

import java.io.DataInputStream;
import java.io.IOException;

public class DeserializePackageOrClass {

    public PackageOrClass[] deserialize(DataInputStream inputStream) throws IOException {
        int size = inputStream.readInt();
        PackageOrClass[] packageOrClasses = new PackageOrClass[size];
        for (int i = 0; i < size; i++) {
            packageOrClasses[i] = PackageOrClass.deserialize(inputStream);
        }

        return packageOrClasses;
    }

}
