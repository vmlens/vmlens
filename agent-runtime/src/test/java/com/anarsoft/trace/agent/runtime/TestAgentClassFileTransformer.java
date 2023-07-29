package com.anarsoft.trace.agent.runtime;

import com.vmlens.trace.agent.bootstrap.callback.FieldAccessCallback;
import com.vmlens.trace.agent.bootstrap.callback.MethodCallback;
import com.vmlens.trace.agent.bootstrap.callback.field.FieldAccessCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.method.MethodCallbackImpl;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestAgentClassFileTransformer {

    private static final int CALLBACK_ID_WRITE_VOLATILE = 3;

    @Test
    public void transformVolatileField() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {

        MethodCallbackImpl methodCallbackImplMock = mock(MethodCallbackImpl.class);
        MethodCallback.setMethodCallbackImpl(methodCallbackImplMock);

        FieldAccessCallbackImpl fieldAccessCallbackImplMock = mock(FieldAccessCallbackImpl.class);
        ;
        FieldAccessCallback.setFieldAccessCallbackImpl(fieldAccessCallbackImplMock);

        runTest("VolatileFieldAccess");

        verify(fieldAccessCallbackImplMock).field_access_from_generated_method(any(), nullable(Long.class), anyInt(), anyInt(), eq(CALLBACK_ID_WRITE_VOLATILE));

    }

    @Test
    public void withAllInterleavings() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        runTest("WithAllInterleavings");
    }

    private void runTest(String className) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        ClassLoader cl = new ClassLoaderForTransformation(this.getClass().getClassLoader());
        Object testTwoVolatileFields = cl.loadClass("com.vmlens.test.guineaPig." + className).newInstance();
        for (Method m : testTwoVolatileFields.getClass().getMethods()) {
            if (m.getName().equals("update")) {
                m.invoke(testTwoVolatileFields);
            }
        }
    }


}
