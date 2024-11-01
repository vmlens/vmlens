package com.anarsoft.trace.agent.runtime;


import com.vmlens.trace.agent.bootstrap.callback.FieldCallback;
import com.vmlens.trace.agent.bootstrap.callback.MethodCallback;
import com.vmlens.trace.agent.bootstrap.callback.impl.FieldCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.impl.MethodCallbackImpl;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

// Fixme activate tests
public class AgentClassFileTransformerIntegTest {

    private static final int CALLBACK_ID_WRITE_VOLATILE = 3;

    @Test
    public void transformVolatileField() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {

        MethodCallbackImpl methodCallbackImplMock = mock(MethodCallbackImpl.class);
        MethodCallback.setMethodCallbackImpl(methodCallbackImplMock);

        FieldCallbackImpl fieldCallbackImplMock = mock(FieldCallbackImpl.class);
        FieldCallback.setFieldCallbackImpl(fieldCallbackImplMock);


        runTest("VolatileFieldAccess");
        verify(fieldCallbackImplMock).beforeFieldRead(any(), anyInt(), anyInt(), anyInt());
        verify(fieldCallbackImplMock).beforeFieldWrite(any(), anyInt(), anyInt(), anyInt());
        verify(fieldCallbackImplMock, times(2)).afterFieldAccess();
    }

    /*
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


     */

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
