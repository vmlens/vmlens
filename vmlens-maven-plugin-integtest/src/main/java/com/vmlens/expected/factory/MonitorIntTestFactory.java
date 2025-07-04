package com.vmlens.expected.factory;

import com.vmlens.expected.domain.TestCase;
import com.vmlens.report.assertion.LeftBeforeRight;
import com.vmlens.report.assertion.Position;

import java.util.HashSet;
import java.util.Set;

public class MonitorIntTestFactory {

    public TestCase create() {
        Set<LeftBeforeRight> expected = new HashSet<>();
        /*
        we have five events:
            thread start 0
            monitor enter 1 / monitor exit 2
            monitor enter 3 / monitor exit 4
         */
        expected.add(new LeftBeforeRight(new Position(0,2), new Position(1,3)));
        expected.add(new LeftBeforeRight(new Position(1,2), new Position(0,3)));
        return new TestCase(expected);
    }

}
