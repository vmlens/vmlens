package com.vmlens.trace.agent.bootstrap.preanalyzed.serialize;

import com.vmlens.trace.agent.bootstrap.preanalyzed.model.PackageOrClass;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.io.DataInputStream;
import java.io.IOException;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class DeserializePackageOrClass {

    public TLinkedList<TLinkableWrapper<PackageOrClass>> deserialize(DataInputStream inputStream) throws IOException {
        int size = inputStream.readInt();
        TLinkedList<TLinkableWrapper<PackageOrClass>> packageOrClasses = new TLinkedList<>();
        for (int i = 0; i < size; i++) {
            packageOrClasses.add(wrap(PackageOrClass.deserialize(inputStream)));
        }
        return packageOrClasses;
    }
}
