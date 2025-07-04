package com.vmlens.trace.agent.bootstrap.interleave.inttest.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ExpectedBuilder {

    private final List<ExpectedElement> expectedElements = new LinkedList<>();

    public void group(IntTestOperation... operations) {
        expectedElements.add(new ExpectedElement(Arrays.asList(operations)));
    }

    public Expected buildExpected() {
        return new Expected(true,expectedElements);
    }

    public Expected buildNotExpected() {
        return new Expected(false,expectedElements);
    }

}
