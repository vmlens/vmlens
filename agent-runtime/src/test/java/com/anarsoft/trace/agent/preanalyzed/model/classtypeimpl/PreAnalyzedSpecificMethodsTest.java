package com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.ClassTransformerListBuilder;
import com.anarsoft.trace.agent.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.ClassType;
import com.anarsoft.trace.agent.preanalyzed.model.PreAnalyzedMethod;
import com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.ThreadStart;
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
