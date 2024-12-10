package com.vmlens.trace.agent.bootstrap.fieldrepository;

import java.util.Objects;

public class FieldOwnerAndName {

    private final String owner;
    private final String name;

    public FieldOwnerAndName(String owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldOwnerAndName that = (FieldOwnerAndName) o;
        return Objects.equals(owner, that.owner) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(owner);
        result = 31 * result + Objects.hashCode(name);
        return result;
    }
}
