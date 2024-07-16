package com.vmlens.trace.agent.bootstrap.interleave;

import java.util.Arrays;

/**
 * for filter using sets
 */
public class IntArray {

    private final int[] array;

    public IntArray(int[] array) {
        this.array = array;
    }

    public static IntArray intArray(int... values) {
        return new IntArray(values);
    }

    public static IntArray wrap(int[] array) {
        return new IntArray(array);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntArray intArray = (IntArray) o;

        return Arrays.equals(array, intArray.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public String toString() {
        return "IntArray{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
