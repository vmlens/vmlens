package com.anarsoft.trace.agent.runtime.classtransformer;

import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryImpl;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.ThreadStartStrategy;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClassTransformerPreAnalyzedTest {

    @Test
    public void threadStart() throws IOException {
        // Given
        RunTestClassTransformer runTestClassTransformer = new RunTestClassTransformer();

        // When
        runTestClassTransformer.runTest("java.lang.Thread", "/thread.txt");

        // Then
        MethodRepositoryImpl repo = runTestClassTransformer.methodRepositoryForAnalyze();
        int id = repo.asInt(new MethodCallId("java/lang/Thread", "start", "()V"));
        assertThat(repo.strategyPreAnalyzed(id), is(ThreadStartStrategy.SINGLETON));
    }
}
