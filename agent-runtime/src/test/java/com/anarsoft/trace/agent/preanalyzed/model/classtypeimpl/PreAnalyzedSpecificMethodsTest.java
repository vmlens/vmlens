package com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.ClassBuilder;
import com.anarsoft.trace.agent.preanalyzed.builder.MethodBuilder;
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
        ClassBuilder classBuilder = mock(ClassBuilder.class);

        MethodBuilder methodBuilder = mock(MethodBuilder.class);
        when(classBuilder.createMethodBuilder()).thenReturn(methodBuilder);

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
