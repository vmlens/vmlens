package com.anarsoft.trace.agent.runtime.applyclassarraytransformer;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApplyApplyClassArrayTransformerCollectionTest {

    @Test
    public void thread() {
        // Given
        ApplyClassArrayTransformerCollection collection = ApplyClassArrayTransformerFactory.retransform().create();

        // When
        ApplyClassArrayTransformer transformer = collection.get("java/lang/Thread");

        // Then
        assertThat(transformer.transformStrategy(), instanceOf(TransformerStrategyThread.class));
    }


}
