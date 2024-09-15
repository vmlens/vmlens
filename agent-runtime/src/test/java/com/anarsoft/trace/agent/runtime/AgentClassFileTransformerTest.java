package com.anarsoft.trace.agent.runtime;

import com.vmlens.trace.agent.bootstrap.callback.*;
import com.vmlens.trace.agent.bootstrap.callback.field.FieldAccessCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.callback.method.MethodCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.obj.HashMapCallback;
import com.vmlens.trace.agent.bootstrap.callback.obj.ObjectCallbackState;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AgentClassFileTransformerTest {

    private static final int CALLBACK_ID_WRITE_VOLATILE = 3;

    @Test
    public void transformVolatileField() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {

        MethodCallbackImpl methodCallbackImplMock = mock(MethodCallbackImpl.class);
        MethodCallback.setMethodCallbackImpl(methodCallbackImplMock);

        FieldAccessCallbackImpl fieldAccessCallbackImplMock = mock(FieldAccessCallbackImpl.class);
        FieldAccessCallback.setFieldAccessCallbackImpl(fieldAccessCallbackImplMock);
        runTest("VolatileFieldAccess");
        verify(fieldAccessCallbackImplMock).field_access_from_generated_method(any(), nullable(Long.class), anyInt(), anyInt(), eq(CALLBACK_ID_WRITE_VOLATILE));

    }

    @Test
    public void withAllInterleavings() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        AgentApiCallbackImpl agentApiCallbackImplMock = mock(AgentApiCallbackImpl.class);
        AgentLogCallback.setAgentApiCallbackImpl(agentApiCallbackImplMock);
        runTest("WithAllInterleavings");
        verify(agentApiCallbackImplMock).hasNext(any());

    }

    @Test
    public void methodCall() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        MethodCallbackImpl methodCallbackImplMock = mock(MethodCallbackImpl.class);
        MethodCallback.setMethodCallbackImpl(methodCallbackImplMock);
        runTest("MethodCall");
        verify(methodCallbackImplMock).methodEnter(anyInt());
        verify(methodCallbackImplMock).methodExit(anyInt());
    }

    @Test
    public void monitorAndWaitNotify() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException, InterruptedException {
        SynchronizedStatementCallbackImpl synchronizedStatementCallbackImplMock = mock(SynchronizedStatementCallbackImpl.class);
        SynchronizedStatementCallback.setImpl(synchronizedStatementCallbackImplMock);
        runTest("MonitorAndWaitNotify");
        verify(synchronizedStatementCallbackImplMock).monitorEnter(any(), anyInt(), anyInt());
        verify(synchronizedStatementCallbackImplMock).monitorExit(any(), anyInt(), anyInt());
        verify(synchronizedStatementCallbackImplMock).waitCall(any(), anyLong(), anyInt());
    }

    @Test
    public void callToHashMap() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        ObjectCallbackState state = mock(ObjectCallbackState.class);
        HashMapCallback.setObjectCallbackState(state);
        runTest("CallToHashMap");
        verify(state).access(any(), eq(MemoryAccessType.IS_READ_WRITE), anyInt());
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
