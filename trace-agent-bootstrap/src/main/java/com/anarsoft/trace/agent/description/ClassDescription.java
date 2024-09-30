package com.anarsoft.trace.agent.description;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.util.Arrays;
import java.util.Objects;

public class ClassDescription implements SerializableEvent {

    private final String name;
    private final String source;
    private final String superClass;
    private final String[] interfaces;
    private final FieldInClassDescription[] serializedFieldDescriptionArray;
    private MethodDescription[] methodArray;

    public ClassDescription(String name, String source,
                            MethodDescription[] methodArray,
                            FieldInClassDescription[] serializedFieldDescriptionArray,
                            String superClass, String[] interfaces) {
        super();
        this.name = name;
        this.source = source;
        this.methodArray = methodArray;
        this.serializedFieldDescriptionArray = serializedFieldDescriptionArray;
        this.superClass = superClass;
        this.interfaces = interfaces;
    }

    @Override
    public void serialize(StreamRepository streamRepository) throws Exception {
        (new SerializeAndDeserializeClassDescription()).serialize(this,
                streamRepository.description.getStream());
    }

    public String name() {
        return name;
    }

    public String source() {
        return source;
    }

    public String superClass() {
        return superClass;
    }

    public String[] interfaces() {
        return interfaces;
    }

    public MethodDescription[] methodArray() {
        return methodArray;
    }

    public FieldInClassDescription[] serializedFieldDescriptionArray() {
		return serializedFieldDescriptionArray;
	}

	@Override
	public String toString() {
		return "ClassAnalyzedEvent [name=" + name + ", methodArray="
				+ Arrays.toString(methodArray) + "]";
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassDescription that = (ClassDescription) o;
        return Objects.equals(name, that.name) && Objects.equals(source, that.source)
                && Objects.equals(superClass, that.superClass) && Arrays.equals(interfaces, that.interfaces)
                && Arrays.equals(serializedFieldDescriptionArray, that.serializedFieldDescriptionArray)
                && Arrays.equals(methodArray, that.methodArray);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(source);
        result = 31 * result + Objects.hashCode(superClass);
        result = 31 * result + Arrays.hashCode(interfaces);
        result = 31 * result + Arrays.hashCode(serializedFieldDescriptionArray);
        result = 31 * result + Arrays.hashCode(methodArray);
        return result;
    }
}
