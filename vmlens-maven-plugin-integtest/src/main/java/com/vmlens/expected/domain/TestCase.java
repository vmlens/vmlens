package com.vmlens.expected.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import com.vmlens.report.assertion.LeftBeforeRight;

public class TestCase {

    private final Set<LeftBeforeRight> expected;

    public TestCase(Set<LeftBeforeRight> expected) {
        this.expected = expected;
    }

    public void found(LeftBeforeRight leftBeforeRight) {
        expected.remove(leftBeforeRight);
    }

    public void check(String name) {
        if(! expected.isEmpty()) {
            System.err.println("check failed for:" + name);
        }

        assertThat(expected,is(empty()));
    }
}
