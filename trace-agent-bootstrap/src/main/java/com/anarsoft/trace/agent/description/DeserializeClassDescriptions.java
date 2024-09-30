package com.anarsoft.trace.agent.description;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DeserializeClassDescriptions {

    public List<ClassDescription> deserialize(DataInputStream inputStream) throws IOException {
        List<ClassDescription> classes = new LinkedList<ClassDescription>();

        SerializeAndDeserializeClassDescription serializeAndDeserializeClassDescription =
                new SerializeAndDeserializeClassDescription();
        try {
            while (true) {
                classes.add(serializeAndDeserializeClassDescription.deserialize(inputStream));
            }
        } catch (EOFException ignored) {

        }
        return classes;
    }

}
