package com.vmlens.expected.domain;

import java.util.HashMap;
import java.util.Map;

public class TestCaseCollection {

    private final Map<String,TestCase> nameToTestCase;

    public TestCaseCollection(Map<String, TestCase> nameToTestCase) {
        this.nameToTestCase = nameToTestCase;
    }

    public TestCase get(String key) {
        return nameToTestCase.get(key);
    }
}
