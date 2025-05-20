package com.anarsoft.trace.agent.runtime.classtransformer;

import org.junit.Test;

import java.io.IOException;


public class ClassTransformerVmlensApiTest {

    @Test
    public void allInterleaving() throws IOException {
        // Given
        RunTestClassTransformer runTestClassTransformer = RunTestClassTransformer.createFromLoaded();

        // When and Then
        runTestClassTransformer.runTest("com.vmlens.api.AllInterleavings", "/allInterleavings.txt");
    }

}
