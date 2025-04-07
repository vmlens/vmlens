package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerStrategy;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerStrategyNoOp;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClassTransformerVmlensApiTest {

    @Test
    public void allInterleavings() throws IOException {
        // Given
        RunTestClassTransformer runTestClassTransformer = RunTestClassTransformer.createFromLoaded();

        // When and Then
        runTestClassTransformer.runTest("com.vmlens.api.AllInterleavings", "/allInterleavings.txt");
    }

    @Test
    public void allInterleavingsBuilder() throws IOException {
        // Given
        RunTestClassTransformer runTestClassTransformer =  RunTestClassTransformer.createFromLoaded();

        // When
        TransformerStrategy strategy = runTestClassTransformer.getStrategy("com.vmlens.api.AllInterleavingsBuilder");

        // Then
        assertThat(strategy, instanceOf(TransformerStrategyNoOp.class));
    }

}
