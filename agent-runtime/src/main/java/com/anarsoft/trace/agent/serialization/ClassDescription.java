package com.anarsoft.trace.agent.serialization;


import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.io.DataOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;


public class ClassDescription implements SerializableEvent {

    final String name;
    final String source;
    final String[] exceptArray;
    final String superClass;
    final String[] interfaces;
    private final SerializedFieldDescription[] serializedFieldDescriptionArray;
    private MethodDescription[] methodArray;


	public ClassDescription(String name, String source, String[] exceptArray, MethodDescription[] methodArray,
                            SerializedFieldDescription[] serializedFieldDescriptionArray, String superClass, String[] interfaces) {
        super();
        this.name = name;
        this.source = source;
        this.exceptArray = exceptArray;
        this.methodArray = methodArray;
        this.serializedFieldDescriptionArray = serializedFieldDescriptionArray;

        this.superClass = superClass;
        this.interfaces = interfaces;


    }


    public void serialize(StreamRepository streamRepository) throws Exception {
        (new SerializeDescription()).serialize(this, streamRepository.description.getStream());
    }

    @Override
    public void serialize(ByteBuffer buffer) throws Exception {

    }

    public void writeToStream(DataOutputStream stream) throws Exception {
        (new SerializeDescription()).serialize(this, stream);
    }

    public MethodDescription[] getMethodArray() {
        return methodArray;
    }

    public SerializedFieldDescription[] getSerializedFieldDescriptionArray() {
		return serializedFieldDescriptionArray;
	}


	@Override
	public String toString() {
		return "ClassAnalyzedEvent [name=" + name + ", methodArray="
				+ Arrays.toString(methodArray) + "]";
	}
}
