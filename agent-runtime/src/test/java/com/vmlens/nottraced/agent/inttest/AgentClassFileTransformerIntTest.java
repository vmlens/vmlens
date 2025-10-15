package com.vmlens.nottraced.agent.inttest;


import com.vmlens.transformed.agent.bootstrap.callback.*;
import com.vmlens.transformed.agent.bootstrap.callback.callbackaction.CallbackActionProcessor;
import com.vmlens.transformed.agent.bootstrap.callback.callbackaction.nomethodaction.*;
import com.vmlens.transformed.agent.bootstrap.callback.callbackaction.methodaction.*;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AgentClassFileTransformerIntTest extends AbstractIntTest{

    @Test
    public void arrayAccess() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {

        CallbackActionProcessorMock callbackActionProcessor = new CallbackActionProcessorMock();

        MethodCallback.setCallbackActionProcessor(callbackActionProcessor);
        ArrayCallback.setCallbackActionProcessor(callbackActionProcessor);

        runTest("ArrayAccess");
        assertThat(callbackActionProcessor.getCount(ArrayAccessAction.class), is(7));
    }


    @Test
    public void transformVolatileField() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {

        CallbackActionProcessorMock callbackActionProcessor = new CallbackActionProcessorMock();
        MethodCallback.setCallbackActionProcessor(callbackActionProcessor);
        FieldCallback.setCallbackActionProcessor(callbackActionProcessor);

        runTest("VolatileFieldAccess");
        assertThat(callbackActionProcessor.getCount(BeforeFieldAccessAction.class), is(2));
        assertThat(callbackActionProcessor.getCount(AfterFieldAccessAction.class), is(2));
    }

    @Test
    public void withAllInterleavings() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {

        CallbackActionProcessor callbackActionProcessor = mock(CallbackActionProcessor.class);
        MethodCallback.setCallbackActionProcessor(callbackActionProcessor);
        VmlensApiCallback.setCallbackActionProcessor(callbackActionProcessor);

        runTest("WithAllInterleavings");
        verify(callbackActionProcessor).vmlensApiHasNext(any());

    }

    @Test
    public void monitorAndWaitNotify() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException  {
        CallbackActionProcessorMock callbackActionProcessor = new CallbackActionProcessorMock();
        MethodCallback.setCallbackActionProcessor(callbackActionProcessor);
        MonitorCallback.setCallbackActionProcessor(callbackActionProcessor);

        runTest("MonitorBlock");
        assertThat(callbackActionProcessor.getCount(AfterMonitorEnterAction.class), is(1));
        assertThat(callbackActionProcessor.getCount(AfterMonitorExitAction.class), is(1));

    }

    @Test
    public void methodCall() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        CallbackActionProcessorMock callbackActionProcessor = new CallbackActionProcessorMock();
        MethodCallback.setCallbackActionProcessor(callbackActionProcessor);

        runTest("MethodCall");
        assertThat(callbackActionProcessor.getCount(MethodEnterAction.class), is(1));
        assertThat(callbackActionProcessor.getCount(MethodExitAction.class), is(1));
    }

    @Test
    public void testVolatileField() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        CallbackActionProcessorMock callbackActionProcessor = new CallbackActionProcessorMock();
        MethodCallback.setCallbackActionProcessor(callbackActionProcessor);
        FieldCallback.setCallbackActionProcessor(callbackActionProcessor);

        runTest("TestVolatileField");
        assertThat(callbackActionProcessor.getCount(BeforeFieldAccessAction.class), is(3));
        assertThat(callbackActionProcessor.getCount(AfterFieldAccessAction.class), is(3));
    }

    @Test
    public void staticMethodCall() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        CallbackActionProcessorMock callbackActionProcessor = new CallbackActionProcessorMock();
        MethodCallback.setCallbackActionProcessor(callbackActionProcessor);

        runTest("StaticMethodCall");
        assertThat(callbackActionProcessor.getCount(MethodEnterAction.class), is(2));
        assertThat(callbackActionProcessor.getCount(MethodExitAction.class), is(2));
    }

    @Test
    public void staticMethodCallWithSynchronizedBlock() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        CallbackActionProcessorMock callbackActionProcessor = new CallbackActionProcessorMock();
        MethodCallback.setCallbackActionProcessor(callbackActionProcessor);

        runTest("StaticMethodCallWithSynchronizedBlock");
        assertThat(callbackActionProcessor.getCount(MethodEnterAction.class), is(2));
        assertThat(callbackActionProcessor.getCount(MethodExitAction.class), is(2));
    }

    @Test
    public void threadPool() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        CallbackActionProcessorMock callbackActionProcessor = new CallbackActionProcessorMock();
        MethodCallback.setCallbackActionProcessor(callbackActionProcessor);
        ThreadPoolCallback.setCallbackActionProcessor(callbackActionProcessor);


        runTest("ThreadPoolExecutorGuineaPig");
        assertThat(callbackActionProcessor.getCount(ThreadPoolStartAction.class), is(1));
        assertThat(callbackActionProcessor.getCount(ThreadPoolJoinAction.class), is(1));
    }

}
