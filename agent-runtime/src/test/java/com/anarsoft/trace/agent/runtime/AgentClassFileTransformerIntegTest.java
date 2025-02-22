package com.anarsoft.trace.agent.runtime;


import com.vmlens.trace.agent.bootstrap.callback.FieldCallback;
import com.vmlens.trace.agent.bootstrap.callback.MethodCallback;
import com.vmlens.trace.agent.bootstrap.callback.MonitorCallback;
import com.vmlens.trace.agent.bootstrap.callback.VmlensApiCallback;
import com.vmlens.trace.agent.bootstrap.callback.impl.FieldCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.impl.MethodCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.impl.MonitorCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.impl.VmlensApiCallbackImpl;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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

    @Test
    public void withAllInterleavings() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {

        MethodCallbackImpl methodCallbackImplMock = mock(MethodCallbackImpl.class);
        MethodCallback.setMethodCallbackImpl(methodCallbackImplMock);
        VmlensApiCallbackImpl vmlensApiCallbackImplMock = mock(VmlensApiCallbackImpl.class);
        VmlensApiCallback.setVmlensApiCallback(vmlensApiCallbackImplMock);

        runTest("WithAllInterleavings");
        verify(vmlensApiCallbackImplMock).hasNext(any());

    }

    @Test
    public void monitorAndWaitNotify() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException, InterruptedException {
        MonitorCallbackImpl monitorCallbackImplMock = mock(MonitorCallbackImpl.class);
        MonitorCallback.setMonitorCallbackImpl(monitorCallbackImplMock);
        runTest("MonitorBlock");
        verify(monitorCallbackImplMock).afterMonitorEnter(any(), anyInt(), anyInt());
        verify(monitorCallbackImplMock).afterMonitorExit(any(), anyInt(), anyInt());

    }

    @Test
    public void methodCall() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        MethodCallbackImpl methodCallbackImplMock = mock(MethodCallbackImpl.class);

        MethodCallback.setMethodCallbackImpl(methodCallbackImplMock);

        runTest("MethodCall");
        verify(methodCallbackImplMock).methodEnter(any(), anyInt());
        verify(methodCallbackImplMock, times(1)).methodExit(any(), anyInt());
    }

    @Test
    public void testVolatileField() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        MethodCallbackImpl methodCallbackImplMock = mock(MethodCallbackImpl.class);
        MethodCallback.setMethodCallbackImpl(methodCallbackImplMock);

        FieldCallbackImpl fieldCallbackImplMock = mock(FieldCallbackImpl.class);
        FieldCallback.setFieldCallbackImpl(fieldCallbackImplMock);

        runTest("TestVolatileField");
    }

    @Test
    public void staticMethodCall() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        MethodCallbackImpl methodCallbackImplMock = mock(MethodCallbackImpl.class);

        MethodCallback.setMethodCallbackImpl(methodCallbackImplMock);

        runTest("StaticMethodCall");
        verify(methodCallbackImplMock, times(2)).methodEnter(any(), anyInt());
        verify(methodCallbackImplMock, times(2)).methodExit(any(), anyInt());
    }

    @Test
    public void staticMethodCallWithSynchronizedBlock() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        MethodCallbackImpl methodCallbackImplMock = mock(MethodCallbackImpl.class);
        MethodCallback.setMethodCallbackImpl(methodCallbackImplMock);

        runTest("StaticMethodCallWithSynchronizedBlock");
        verify(methodCallbackImplMock, times(2)).methodEnter(any(), anyInt());
        verify(methodCallbackImplMock, times(2)).methodExit(any(), anyInt());
    }

    private void runTest(String className) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        ClassLoader cl = new ClassLoaderForTransformation(this.getClass().getClassLoader());
        Object objectUnderTest = cl.loadClass("com.vmlens.test.guineapig." + className).newInstance();
        for (Method m : objectUnderTest.getClass().getMethods()) {
            if (m.getName().equals("update")) {
                m.invoke(objectUnderTest);
            }
        }
    }
}
