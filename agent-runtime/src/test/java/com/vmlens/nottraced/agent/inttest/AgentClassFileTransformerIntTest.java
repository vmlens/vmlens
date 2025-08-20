package com.vmlens.nottraced.agent.inttest;


import com.vmlens.trace.agent.bootstrap.callback.*;
import com.vmlens.trace.agent.bootstrap.callback.impl.*;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class AgentClassFileTransformerIntTest extends AbstractIntTest{

    @Test
    public void arrayAccess() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {

        MethodCallbackImpl methodCallbackImplMock = mock(MethodCallbackImpl.class);
        MethodCallback.setMethodCallbackImpl(methodCallbackImplMock);

        ArrayCallbackImpl arrayCallbackImpl = mock(ArrayCallbackImpl.class);
        ArrayCallback.setArrayCallbackImpl(arrayCallbackImpl);

        runTest("ArrayAccess");
        verify(arrayCallbackImpl, times(4)).beforeArrayWrite(any(), anyInt(), anyInt());
        verify(arrayCallbackImpl, times(3)).beforeArrayRead(any(), anyInt(), anyInt());
    }


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

    //@Test
    public void staticMethodCall() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        MethodCallbackImpl methodCallbackImplMock = mock(MethodCallbackImpl.class);

        MethodCallback.setMethodCallbackImpl(methodCallbackImplMock);

        runTest("StaticMethodCall");
        verify(methodCallbackImplMock, times(2)).methodEnter(any(), anyInt());
        verify(methodCallbackImplMock, times(2)).methodExit(any(), anyInt());
    }

    //@Test
    public void staticMethodCallWithSynchronizedBlock() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        MethodCallbackImpl methodCallbackImplMock = mock(MethodCallbackImpl.class);
        MethodCallback.setMethodCallbackImpl(methodCallbackImplMock);

        runTest("StaticMethodCallWithSynchronizedBlock");
        verify(methodCallbackImplMock, times(2)).methodEnter(any(), anyInt());
        verify(methodCallbackImplMock, times(2)).methodExit(any(), anyInt());
    }

    //@Test
    public void threadPool() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        ThreadPoolCallbackImpl threadPoolCallbackImpl = mock(ThreadPoolCallbackImpl.class);
        ThreadPoolCallback.setThreadPoolCallbackImpl(threadPoolCallbackImpl);
        when(threadPoolCallbackImpl.start(any(), any(),anyInt())).thenReturn(true);

        runTest("ThreadPoolExecutorGuineaPig");
        verify(threadPoolCallbackImpl, times(1)).start(any(), any(),anyInt());
        verify(threadPoolCallbackImpl, times(1)).join(any(), anyInt());
    }



}
