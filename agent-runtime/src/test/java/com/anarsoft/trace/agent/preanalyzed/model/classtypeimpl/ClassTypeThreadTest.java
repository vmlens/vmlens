package com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.ClassBuilder;
import com.anarsoft.trace.agent.preanalyzed.builder.MethodBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.ClassType;
import com.anarsoft.trace.agent.preanalyzed.model.PreAnalyzedMethod;
import com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.MethodTypeThreadStart;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ClassTypeThreadTest {

    @Test
    public void add() {
        // Given
        ClassType classTypeThread = ClassTypeThread.SINGLETON;
        ClassBuilder classBuilder = mock(ClassBuilder.class);

        MethodBuilder methodBuilder = mock(MethodBuilder.class);
        when(classBuilder.createThread(anyString())).thenReturn(methodBuilder);

        PreAnalyzedMethod[] methods = new PreAnalyzedMethod[1];
        methods[0] = new PreAnalyzedMethod("start", "()V", MethodTypeThreadStart.SINGLETON);

        // When
        classTypeThread.add("java.lang.Thread", methods, classBuilder);

        // Then
        verify(classBuilder).createThread("java.lang.Thread");
        verify(methodBuilder).addThreadStart("start", "()V");
        verify(methodBuilder).doNothingWhenNotFound();
    }


}
