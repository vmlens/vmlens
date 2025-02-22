package com.anarsoft.trace.agent.preanalyzed.model;

import com.anarsoft.trace.agent.preanalyzed.builder.ClassBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.AbstractClassType;
import org.visualj.ViewComponent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@ViewComponent("agent.runtime")
public class PackageOrClass {

    private final String name;
    private final ClassType classType;
    private final PreAnalyzedMethod[] methods;

    public PackageOrClass(String name,
                          ClassType classType,
                          PreAnalyzedMethod[] methods) {
        this.name = name;
        this.classType = classType;
        this.methods = methods;
    }

    public static PackageOrClass deserialize(DataInputStream inputStream) throws IOException {
        String name = inputStream.readUTF();
        ClassType classType = AbstractClassType.deserialize(inputStream);
        int size = inputStream.readInt();
        PreAnalyzedMethod[] methods = new PreAnalyzedMethod[size];
        for (int i = 0; i < size; i++) {
            methods[i] = PreAnalyzedMethod.deserialize(inputStream);
        }
        return new PackageOrClass(name, classType, methods);

    }

    public void addToBuilder(ClassBuilder classBuilder) {
        classType.addToBuilder(name, methods, classBuilder);
    }

    public void serialize(DataOutputStream out) throws IOException {
        out.writeUTF(name);
        classType.serialize(out);
        out.writeInt(methods.length);
        for (PreAnalyzedMethod method : methods) {
            method.serialize(out);
        }
    }

    @Override
    public String toString() {
        return "PackageOrClass{" +
                "name='" + name + '\'' +
                ", classType=" + classType +
                ", methods=" + Arrays.toString(methods) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PackageOrClass that = (PackageOrClass) o;

        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(classType, that.classType)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(methods, that.methods);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (classType != null ? classType.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(methods);
        return result;
    }
}
