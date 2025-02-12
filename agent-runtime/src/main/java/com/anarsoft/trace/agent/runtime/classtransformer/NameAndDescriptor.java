package com.anarsoft.trace.agent.runtime.classtransformer;

import java.util.Objects;

public class NameAndDescriptor {
    private final String name;
    private final String descriptor;


    public NameAndDescriptor(String name, String descriptor) {
        this.name = name;
        this.descriptor = descriptor;
    }

    public String name() {
        return name;
    }

    public String descriptor() {
        return descriptor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NameAndDescriptor that = (NameAndDescriptor) o;

        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(descriptor, that.descriptor);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (descriptor != null ? descriptor.hashCode() : 0);
        return result;
    }
}
