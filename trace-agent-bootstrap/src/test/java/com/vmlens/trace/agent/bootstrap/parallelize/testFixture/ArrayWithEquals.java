package com.vmlens.trace.agent.bootstrap.parallelize.testFixture;

import java.util.Arrays;

public class ArrayWithEquals<ELEMENTS> {

    private final ELEMENTS[] array;

    public ArrayWithEquals(ELEMENTS[] array) {
        this.array = array;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayWithEquals<?> that = (ArrayWithEquals<?>) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
