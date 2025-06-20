package com.vmlens.expected.factory;

import com.vmlens.report.assertion.LeftBeforeRight;
import com.vmlens.expected.domain.TestCase;
import com.vmlens.report.assertion.Position;

import java.util.HashSet;
import java.util.Set;

public class VolatileFieldIntTestFactory {

    public TestCase create() {
        Set<LeftBeforeRight> expected = new HashSet<>();
        /*
        we have three events:
            thread start
            read or write
         */
        expected.add(new LeftBeforeRight(new Position(0,1), new Position(1,2)));
        expected.add(new LeftBeforeRight(new Position(1,1), new Position(0,2)));
        return new TestCase(expected);
    }

}
