package com.anarsoft.trace.agent.description;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SerializeAndDeserializeClassDescription {

    public void serialize(ClassDescription description, DataOutputStream out) throws IOException {
        out.writeUTF(convertNullToBlank(description.name()));
        out.writeUTF(description.source());

        out.writeUTF(convertNullToBlank(description.superClass()));
        out.writeInt(description.interfaces().length);

        for (String name : description.interfaces()) {
            out.writeUTF(convertNullToBlank(name));
        }

        out.writeInt(description.methodArray().length);
        for (MethodDescription md : description.methodArray()) {
            serialize(md, out);
        }

        out.writeInt(description.serializedFieldDescriptionArray().length);
        for (FieldInClassDescription md : description.serializedFieldDescriptionArray()) {
            serialize(md, out);
        }
        out.flush();
    }

    public ClassDescription deserialize(DataInputStream in) throws IOException {
        String name = in.readUTF();
        String source = in.readUTF();
        String superClass = in.readUTF();

        int interfaceArrayLength = in.readInt();
        String[] interfaces = new String[interfaceArrayLength];

        for (int i = 0; i < interfaceArrayLength; i++) {
            interfaces[i] = in.readUTF();
        }

        int methodArrayLength = in.readInt();
        MethodDescription[] methodArray = new MethodDescription[methodArrayLength];

        for (int i = 0; i < methodArrayLength; i++) {
            methodArray[i] = deserializeMethod(in);
        }

        int fieldArrayLength = in.readInt();
        FieldInClassDescription[] fieldArray = new FieldInClassDescription[fieldArrayLength];

        for (int i = 0; i < fieldArrayLength; i++) {
            fieldArray[i] = deserializeField(in);
        }

        return new ClassDescription(name, source, methodArray, fieldArray,
                superClass, interfaces);
    }

    private void serialize(MethodDescription description, DataOutputStream out) throws IOException {
        out.writeUTF(convertNullToBlank(description.name()));
        out.writeInt(description.id());
        out.writeUTF(convertNullToBlank(description.desc()));
        out.writeInt(description.access());
        out.writeInt(description.lineNumber());

    }

    private MethodDescription deserializeMethod(DataInputStream in) throws IOException {
        String name = in.readUTF();
        int id = in.readInt();
        String desc = in.readUTF();
        int access = in.readInt();
        int lineNumber = in.readInt();

        return new MethodDescription(name, id,
                desc, access, lineNumber);
    }

    private void serialize(FieldInClassDescription description, DataOutputStream out) throws IOException {
        out.writeUTF(convertNullToBlank(description.name()));
        out.writeInt(description.id());
        out.writeUTF(convertNullToBlank(description.desc()));
        out.writeInt(description.access());
        out.writeUTF(convertNullToBlank(description.signature()));
    }

    private FieldInClassDescription deserializeField(DataInputStream in) throws IOException {
        String name = in.readUTF();
        int id = in.readInt();
        String desc = in.readUTF();
        int access = in.readInt();
        String signature = in.readUTF();

        return new FieldInClassDescription(id, access, name, desc, signature);
    }




    private String convertNullToBlank(String in) {
        if (in == null) {
            return "";
        }
        return in;
    }

}
