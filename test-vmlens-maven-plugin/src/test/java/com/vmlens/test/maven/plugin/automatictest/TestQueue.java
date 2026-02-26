package com.vmlens.test.maven.plugin.automatictest;

import com.vmlens.api.automatic.AutomaticTestBuilder;
import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;

public class TestQueue {

    @Test
    public void testNullValues() {
        new AutomaticTestBuilder<>(ConcurrentLinkedQueue::new)
                .addWrite((obj) -> obj.add("test") )
                .addReadOnly(ConcurrentLinkedQueue::peek)
                .runTests();
    }

    @Test
    public void testExceptions() {
        new AutomaticTestBuilder<>(ConcurrentLinkedQueue::new)
                .addWrite((obj) -> obj.add("test") )
                .addReadOnly(ConcurrentLinkedQueue::element)
                .runTests();
    }

}
