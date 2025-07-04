package com.vmlens.nottraced.agent.classtransformer;

import org.junit.Test;

import java.io.IOException;

public class ClassTransformerPreAnalyzedTest {

    @Test
    public void atomicInteger() throws IOException {
        // Given
        RunTestClassTransformer runTestClassTransformer =  RunTestClassTransformer.createFromLoaded();

        // When and Then
        runTestClassTransformer.runTest("java.util.concurrent.atomic.AtomicInteger", "/atomicInteger.txt");
    }

    @Test
    public void atomicArray() throws IOException {
        // Given
        RunTestClassTransformer runTestClassTransformer =  RunTestClassTransformer.createFromLoaded();

        // When and Then
        runTestClassTransformer.runTest("com.vmlens.test.guineapig.MethodWithIntParam", "/methodWithIntParam.txt");
    }

}
