package com.vmlens.nottraced.agent.inttest;

import com.vmlens.transformed.agent.bootstrap.callback.MethodCallback;
import com.vmlens.transformed.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.transformed.agent.bootstrap.callback.callbackaction.CallbackActionProcessor;
import com.vmlens.transformed.agent.bootstrap.callback.callbackaction.methodaction.MethodEnterAction;
import com.vmlens.transformed.agent.bootstrap.callback.callbackaction.methodaction.MethodExitAction;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TryCatchFinallyIntTest extends AbstractIntTest {

    @Test
    public void normalCall() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        CallbackActionProcessor callbackActionProcessor = mock(CallbackActionProcessor.class);
        MethodCallback.setCallbackActionProcessor(callbackActionProcessor);

        runTest("trycatchfinally.NormalCall");
        verify(callbackActionProcessor,times(6)).process(any());
    }

    @Test
    public void finalBlockThree() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        CallbackActionProcessor callbackActionProcessor = mock(CallbackActionProcessor.class);
        MethodCallback.setCallbackActionProcessor(callbackActionProcessor);

        runTest("trycatchfinally.FinalBlockThree");
        verify(callbackActionProcessor,times(6)).process(any());
    }

    @Test
    public void finalBlockTwo() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        CallbackActionProcessor callbackActionProcessor = mock(CallbackActionProcessor.class);
        MethodCallback.setCallbackActionProcessor(callbackActionProcessor);

        runTest("trycatchfinally.FinalBlockTwo");
        verify(callbackActionProcessor,times(6)).process(any());
    }

    @Ignore
    @Test
    public void withException() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        CallbackActionProcessor callbackActionProcessor = mock(CallbackActionProcessor.class);
        MethodCallback.setCallbackActionProcessor(callbackActionProcessor);

        runTest("trycatchfinally.WithException");
        verify(callbackActionProcessor,times(10)).process(any());
    }

    @Ignore
    @Test
    public void tryCatchAndFinally() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        CallbackActionProcessor callbackActionProcessor = mock(CallbackActionProcessor.class);
        MethodCallback.setCallbackActionProcessor(callbackActionProcessor);

        runTest("trycatchfinally.TryCatchAndFinally");

        Map<Integer,EnterExitCallCount> methodIdToEnterExitCallCount = new HashMap<>() ;
        ArgumentCaptor<CallbackAction> captor = ArgumentCaptor.forClass(CallbackAction.class);
        verify(callbackActionProcessor,times(14)).process(captor.capture());
        for(CallbackAction callbackAction :   captor.getAllValues() ) {
            if(callbackAction instanceof MethodEnterAction) {
                MethodEnterAction methodEnterAction = (MethodEnterAction) callbackAction;
                EnterExitCallCount count = methodIdToEnterExitCallCount.computeIfAbsent(methodEnterAction.methodId(), EnterExitCallCount::new);
                count.enterCount++;
                methodIdToEnterExitCallCount.put(methodEnterAction.methodId(), count);
            }
            if(callbackAction instanceof MethodExitAction) {
                MethodExitAction methodEnterAction = (MethodExitAction) callbackAction;
                EnterExitCallCount count = methodIdToEnterExitCallCount.computeIfAbsent(methodEnterAction.methodId(), EnterExitCallCount::new);
                count.exitCount++;
                methodIdToEnterExitCallCount.put(methodEnterAction.methodId(), count);
            }
        }
        for(EnterExitCallCount count : methodIdToEnterExitCallCount.values()) {
            assertThat(count.enterCount,is(1));
            assertThat(count.exitCount,is(1));
        }
    }

    @Ignore
    @Test
    public void multipleCallWithException() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        CallbackActionProcessor callbackActionProcessor = mock(CallbackActionProcessor.class);
        MethodCallback.setCallbackActionProcessor(callbackActionProcessor);

        runTest("trycatchfinally.MultipleCallWithException");

        Map<Integer,EnterExitCallCount> methodIdToEnterExitCallCount = new HashMap<>() ;
        ArgumentCaptor<CallbackAction> captor = ArgumentCaptor.forClass(CallbackAction.class);
        verify(callbackActionProcessor,times(17)).process(captor.capture());
        for(CallbackAction callbackAction :   captor.getAllValues() ) {
            if(callbackAction instanceof MethodEnterAction) {
                MethodEnterAction methodEnterAction = (MethodEnterAction) callbackAction;
                EnterExitCallCount count = methodIdToEnterExitCallCount.computeIfAbsent(methodEnterAction.methodId(), EnterExitCallCount::new);
                count.enterCount++;
                methodIdToEnterExitCallCount.put(methodEnterAction.methodId(), count);
            }
            if(callbackAction instanceof MethodExitAction) {
                MethodExitAction methodEnterAction = (MethodExitAction) callbackAction;
                EnterExitCallCount count = methodIdToEnterExitCallCount.computeIfAbsent(methodEnterAction.methodId(), EnterExitCallCount::new);
                count.exitCount++;
                methodIdToEnterExitCallCount.put(methodEnterAction.methodId(), count);
            }
        }
        for(EnterExitCallCount count : methodIdToEnterExitCallCount.values()) {
            assertThat(count.enterCount,is(6));
            assertThat(count.exitCount,is(6));
        }
    }


}
