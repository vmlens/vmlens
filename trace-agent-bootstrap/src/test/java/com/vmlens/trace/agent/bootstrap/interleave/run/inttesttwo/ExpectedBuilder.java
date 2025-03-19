package com.vmlens.trace.agent.bootstrap.interleave.run.inttesttwo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ExpectedBuilder {

    private final List<ExpectedElement> expectedElements = new LinkedList<>();

    public void group(IntTestOperation... operations) {
        expectedElements.add(new ExpectedElement(Arrays.asList(operations)));
    }

    public Expected build() {
        return new Expected(expectedElements);
    }

}
