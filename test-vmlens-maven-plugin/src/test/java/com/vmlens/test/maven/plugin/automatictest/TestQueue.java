package com.vmlens.test.maven.plugin.automatictest;

import com.vmlens.api.testbuilder.AtomicTestBuilder;
import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;

public class TestQueue {

    @Test
    public void testNullValues() {
        new AtomicTestBuilder<>(ConcurrentLinkedQueue::new)
                .addWrite((obj) -> obj.add("test") )
                .addReadOnly(ConcurrentLinkedQueue::peek)
                .runTests();
    }

    @Test
    public void testExceptions() {
        new AtomicTestBuilder<>(ConcurrentLinkedQueue::new)
                .addWrite((obj) -> obj.add("test") )
                .addReadOnly(ConcurrentLinkedQueue::element)
                .runTests();
    }

}
