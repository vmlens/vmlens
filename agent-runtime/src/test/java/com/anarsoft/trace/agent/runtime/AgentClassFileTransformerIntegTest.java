package com.anarsoft.trace.agent.runtime;

import com.vmlens.trace.agent.bootstrap.callbackdeprecated.FieldAccessCallback;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.field.FieldAccessCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.field.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.method.MethodCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.obj.HashMapCallback;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.obj.ObjectCallbackState;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

// Fixme activate tests
public class AgentClassFileTransformerIntegTest {

    private static final int CALLBACK_ID_WRITE_VOLATILE = 3;

    //@Test
    public void transformVolatileField() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {

        MethodCallbackImpl methodCallbackImplMock = mock(MethodCallbackImpl.class);
        com.vmlens.trace.agent.bootstrap.callbackdeprecated.MethodCallback.setMethodCallbackImpl(methodCallbackImplMock);

        FieldAccessCallbackImpl fieldAccessCallbackImplMock = mock(FieldAccessCallbackImpl.class);
        FieldAccessCallback.setFieldAccessCallbackImpl(fieldAccessCallbackImplMock);
        runTest("VolatileFieldAccess");
        verify(fieldAccessCallbackImplMock).field_access_from_generated_method(any(), nullable(Long.class), anyInt(),
                anyInt(), eq(CALLBACK_ID_WRITE_VOLATILE));

    }

    //@Test
    public void withAllInterleavings() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        com.vmlens.trace.agent.bootstrap.callbackdeprecated.AgentApiCallbackImpl agentApiCallbackImplMock = mock(com.vmlens.trace.agent.bootstrap.callbackdeprecated.AgentApiCallbackImpl.class);
        com.vmlens.trace.agent.bootstrap.callbackdeprecated.AgentLogCallback.setAgentApiCallbackImpl(agentApiCallbackImplMock);
        runTest("WithAllInterleavings");
        verify(agentApiCallbackImplMock).hasNext(any());

    }

    //@Test
    public void methodCall() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        MethodCallbackImpl methodCallbackImplMock = mock(MethodCallbackImpl.class);
        com.vmlens.trace.agent.bootstrap.callbackdeprecated.MethodCallback.setMethodCallbackImpl(methodCallbackImplMock);
        runTest("MethodCall");
        verify(methodCallbackImplMock).methodEnter(anyInt());
        verify(methodCallbackImplMock).methodExit(anyInt());
    }

    //@Test
    public void monitorAndWaitNotify() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException, InterruptedException {
        com.vmlens.trace.agent.bootstrap.callbackdeprecated.SynchronizedStatementCallbackImpl synchronizedStatementCallbackImplMock = mock(com.vmlens.trace.agent.bootstrap.callbackdeprecated.SynchronizedStatementCallbackImpl.class);
        com.vmlens.trace.agent.bootstrap.callbackdeprecated.SynchronizedStatementCallback.setImpl(synchronizedStatementCallbackImplMock);
        runTest("MonitorAndWaitNotify");
        verify(synchronizedStatementCallbackImplMock).monitorEnter(any(), anyInt(), anyInt());
        verify(synchronizedStatementCallbackImplMock).monitorExit(any(), anyInt(), anyInt());
        verify(synchronizedStatementCallbackImplMock).waitCall(any(), anyLong(), anyInt());
    }

    //@Test
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
