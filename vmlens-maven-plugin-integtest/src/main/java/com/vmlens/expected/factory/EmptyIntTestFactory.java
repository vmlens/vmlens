package com.vmlens.expected.factory;

import com.vmlens.expected.domain.TestCase;
import com.vmlens.report.assertion.LeftBeforeRight;
import com.vmlens.report.assertion.Position;

import java.util.HashSet;
import java.util.Set;

public class EmptyIntTestFactory {

    public TestCase create() {
        Set<LeftBeforeRight> expected = new HashSet<>();
        return new TestCase(expected);
    }

}
