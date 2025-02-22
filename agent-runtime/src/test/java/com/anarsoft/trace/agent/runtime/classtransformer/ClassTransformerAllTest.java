package com.anarsoft.trace.agent.runtime.classtransformer;


import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryImpl;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.NormalMethodStrategy;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClassTransformerAllTest {

    @Test
    public void methodCall() throws IOException {
        // Given
        RunTestClassTransformer runTestClassTransformer = new RunTestClassTransformer();

        // When
        runTestClassTransformer.runTest("com.vmlens.test.guineapig.MethodCall", "/methodCall.txt");

        // Then
        MethodRepositoryImpl repo = runTestClassTransformer.methodRepositoryForAnalyze();
        int id = repo.asInt(new MethodCallId("com/vmlens/test/guineapig/MethodCall", "update", "()V"));
        assertThat(repo.strategyAll(id), is(NormalMethodStrategy.SINGLETON));
    }


}
