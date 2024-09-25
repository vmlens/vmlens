package com.anarsoft.trace.agent.description;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.util.Arrays;

public class ClassDescription implements SerializableEvent {

    final String name;
    final String source;
    final String[] exceptArray;
    final String superClass;
    final String[] interfaces;
    private final FieldInClassDescription[] serializedFieldDescriptionArray;
    private MethodDescription[] methodArray;

	public ClassDescription(String name, String source, String[] exceptArray, MethodDescription[] methodArray,
                            FieldInClassDescription[] serializedFieldDescriptionArray, String superClass, String[] interfaces) {
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
        (new SerializeDescription()).serialize(this,
                streamRepository.description.getStream());
    }

    public MethodDescription[] getMethodArray() {
        return methodArray;
    }

    public FieldInClassDescription[] getSerializedFieldDescriptionArray() {
		return serializedFieldDescriptionArray;
	}

	@Override
	public String toString() {
		return "ClassAnalyzedEvent [name=" + name + ", methodArray="
				+ Arrays.toString(methodArray) + "]";
	}
}
