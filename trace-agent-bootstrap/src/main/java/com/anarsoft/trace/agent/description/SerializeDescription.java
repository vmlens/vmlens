package com.anarsoft.trace.agent.description;


import java.io.DataOutputStream;
import java.io.IOException;

public class SerializeDescription {

	public void serialize(ClassDescription description, DataOutputStream out) throws IOException {
		out.writeUTF(description.name);
		out.writeUTF(description.source);
		out.writeInt(description.exceptArray.length );
		
		for(  String except : description.exceptArray )
		{
			out.writeUTF(except);
		}
		
		out.writeUTF(description.superClass);
		out.writeInt(description.interfaces.length);
		
		for (String name : description.interfaces) {
            out.writeUTF(name);
        }

		out.writeInt(description.getMethodArray().length);
		for (MethodDescription md : description.getMethodArray()) {
            serialize(md, out);
        }
		
		out.writeInt(description.getSerializedFieldDescriptionArray().length);
		for (FieldInClassDescription md : description.getSerializedFieldDescriptionArray()) {
            serialize(md, out);
        }
		out.flush();
	}

	private void serialize(MethodDescription description, DataOutputStream out) throws IOException {
		out.writeUTF(description.getName());
		out.writeInt(description.getId());
		out.writeUTF(description.getDesc());
		out.writeInt(description.getAccess());
		out.writeInt(description.getLineNumber());
		out.writeInt(description.getFieldArray().length);

		for (FieldAccessDescription md : description.getFieldArray()) {
			serialize(md,out);
		}
	}

	private void serialize(FieldInClassDescription description, DataOutputStream out) throws IOException {
		out.writeUTF(description.getName());
		out.writeInt(description.getId());
		out.writeUTF(description.getDesc());
		out.writeInt(description.getAccess());
	}

	private void serialize(FieldAccessDescription description, DataOutputStream out) throws IOException {
		out.writeUTF(description.getName());
		out.writeUTF(description.getOwner());
		out.writeInt(description.getId());
		out.writeBoolean(description.isStatic());
		out.writeBoolean(description.isWrite());
		out.writeBoolean(description.isTraced());
		out.writeBoolean(description.isFinal());
	}
}
