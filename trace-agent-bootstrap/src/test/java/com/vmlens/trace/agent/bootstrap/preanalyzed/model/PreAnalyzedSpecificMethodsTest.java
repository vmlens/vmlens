package com.vmlens.trace.agent.bootstrap.preanalyzed.model;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.ClassTransformerListBuilder;
import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.ClassType;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.PreAnalyzedMethod;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.PreAnalyzedSpecificMethods;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.ThreadStart;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class PreAnalyzedSpecificMethodsTest {

    @Test
    public void add() {
        // Given
        ClassType classTypeThread = PreAnalyzedSpecificMethods.SINGLETON;
        ClassTransformerListBuilder classBuilder = mock(ClassTransformerListBuilder.class);

        FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder = mock(FactoryCollectionPreAnalyzedFactoryBuilder.class);
        when(classBuilder.createTraceNoMethodCall()).thenReturn(methodBuilder);

        PreAnalyzedMethod[] methods = new PreAnalyzedMethod[1];
        methods[0] = new PreAnalyzedMethod("start", "()V", ThreadStart.SINGLETON);

        // When
        classTypeThread.addToBuilder("java.lang.Thread", methods, classBuilder);

        // Then
        verify(classBuilder).addPreAnalyzedEquals(eq("java.lang.Thread"), any());
        verify(methodBuilder).addThreadStart("start", "()V");
        verify(methodBuilder).noOpWhenMethodNotFound();
    }

}
