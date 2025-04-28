package com.anarsoft.trace.agent.runtime.classtransformer;

import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryImpl;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.NormalMethodStrategy;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClassTransformerPreAnalyzedTest {

    @Test
    public void atomicInteger() throws IOException {
        // Given
        RunTestClassTransformer runTestClassTransformer =  RunTestClassTransformer.createFromLoaded();

        // When and Then
        runTestClassTransformer.runTest("java.util.concurrent.atomic.AtomicInteger", "/atomicInteger.txt");

    }

}
