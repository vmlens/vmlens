package com.vmlens.api.testbuilder.internal.runner;

import org.junit.jupiter.api.Test;

import java.util.List;

public class IntegerPartitionsTest {

    @Test
    public void sizeOne() {
        // When
        List<List<Integer>> partitions = new IntegerPartitions().partitions(1);

        // Then
        System.out.println(partitions);
    }

    @Test
    public void sizeTwo() {
        // When
        List<List<Integer>> partitions = new IntegerPartitions().partitions(2);

        // Then
        System.out.println(partitions);
    }

}
