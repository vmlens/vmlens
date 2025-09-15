package com.vmlens.nottraced.agent.inttest;

import com.vmlens.trace.agent.bootstrap.callback.FieldCallback;
import com.vmlens.trace.agent.bootstrap.callback.MethodCallback;
import com.vmlens.trace.agent.bootstrap.callback.impl.FieldCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.impl.MethodCallbackImpl;

import java.lang.reflect.InvocationTargetException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class ConstructorCallTest extends AbstractIntTest  {

    @Test
    public void constructorParent() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        MethodCallbackImpl methodCallbackImplMock = mock(MethodCallbackImpl.class);
        MethodCallback.setMethodCallbackImpl(methodCallbackImplMock);

        runTest("ConstructorParent");
        verify(methodCallbackImplMock, times(4)).constructorMethodEnter(anyInt());
        verify(methodCallbackImplMock, times(4)).constructorMethodExit(anyInt());
    }

    //@Test
    public void constructorChild() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        MethodCallbackImpl methodCallbackImplMock = mock(MethodCallbackImpl.class);
        MethodCallback.setMethodCallbackImpl(methodCallbackImplMock);

        runTest("ConstructorChild");
        verify(methodCallbackImplMock, times(2)).constructorMethodEnter(anyInt());
        verify(methodCallbackImplMock, times(2)).constructorMethodExit(any());

    }

    public void constructorWithField() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        MethodCallbackImpl methodCallbackImplMock = mock(MethodCallbackImpl.class);
        MethodCallback.setMethodCallbackImpl(methodCallbackImplMock);

        FieldCallbackImpl fieldCallbackImplMock = mock(FieldCallbackImpl.class);
        FieldCallback.setFieldCallbackImpl(fieldCallbackImplMock);

        runTest("ConstructorWithField");
        verify(fieldCallbackImplMock).beforeFieldWrite(any(), anyInt(), anyInt(), anyInt());
        verify(fieldCallbackImplMock).afterFieldAccess();
    }

}
