package com.vmlens.api.testbuilder.internal.runner;

import java.util.ArrayList;
import java.util.List;

public class IntegerPartitions {

    public List<List<Integer>> partitions(int size) {
        List<List<Integer>> result = new ArrayList<>();
        generate(size + 1,  size, new ArrayList<>(), result);
        return result;
    }

    private void generate(int remaining,
                                 int maxAllowed,
                                 List<Integer> current,
                                 List<List<Integer>> result) {

        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = Math.min(maxAllowed, remaining); i >= 1; i--) {
            current.add(i);
            generate(remaining - i, i, current, result);
            current.remove(current.size() - 1);
        }
    }

}
