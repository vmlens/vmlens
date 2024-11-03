package com.anarsoft.trace.agent.runtime.applyclasstransformer;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApplyClassTransformerElementCollectionTest {

    @Test
    public void thread() {
        // Given
        ApplyClassTransformerCollection collection = ApplyClassTransformerElementFactory.retransform().create();

        // When
        ApplyClassTransformerElement transformer = collection.get("java/lang/Thread");

        // Then
        assertThat(transformer.transformStrategy(), instanceOf(TransformerStrategyThread.class));
    }


}
