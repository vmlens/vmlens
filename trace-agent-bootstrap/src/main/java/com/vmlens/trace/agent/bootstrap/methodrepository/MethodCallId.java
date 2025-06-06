package com.vmlens.trace.agent.bootstrap.methodrepository;

import java.util.Objects;

public class MethodCallId {
    private final String owner;
    private final String name;
    private final String descriptor;

    public MethodCallId(String owner, String name, String descriptor) {
        this.owner = owner;
        this.name = name;
        this.descriptor = descriptor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodCallId that = (MethodCallId) o;
        return Objects.equals(owner, that.owner) && Objects.equals(name, that.name) &&
                Objects.equals(descriptor, that.descriptor);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(owner);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(descriptor);
        return result;
    }

    public String name() {
        return name;
    }

    public String descriptor() {
        return descriptor;
    }
}
