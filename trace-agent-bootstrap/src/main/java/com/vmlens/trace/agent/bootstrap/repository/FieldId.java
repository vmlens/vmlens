package com.vmlens.trace.agent.bootstrap.repository;

import java.util.Objects;

public class FieldId {

    private final String owner;
    private final String name;
    private final String descriptor;

    public FieldId(String owner, String name, String descriptor) {
        this.owner = owner;
        this.name = name;
        this.descriptor = descriptor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldId fieldId = (FieldId) o;
        return Objects.equals(owner, fieldId.owner) && Objects.equals(name, fieldId.name) && Objects.equals(descriptor, fieldId.descriptor);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(owner);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(descriptor);
        return result;
    }
}
