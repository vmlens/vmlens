package com.vmlens.trace.agent.bootstrap.interleave.run.inttesttwo;

import java.util.List;
import java.util.Objects;

public class ExpectedElement {

    private final List<IntTestOperation> intTestOperations;

    public ExpectedElement(List<IntTestOperation> intTestOperations) {
        this.intTestOperations = intTestOperations;
    }

    public PotentialElement asPotential() {
        return new PotentialElement(intTestOperations);
    }

    @Override
    public String toString() {
        return "ExpectedElement{" +
                "intTestOperations=" + intTestOperations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        ExpectedElement that = (ExpectedElement) o;
        return Objects.equals(intTestOperations, that.intTestOperations);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(intTestOperations);
    }
}
