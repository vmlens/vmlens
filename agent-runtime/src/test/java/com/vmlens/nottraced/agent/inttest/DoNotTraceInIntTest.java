package com.vmlens.nottraced.agent.inttest;

import com.vmlens.trace.agent.bootstrap.callback.DoNotTraceCallback;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessor;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DoNotTraceInIntTest extends AbstractIntTest  {

    @Test
    public void whenDoNotTraceApplies_thenDoNotTraceGetCalled() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        CallbackActionProcessor callbackActionProcessor = mock(CallbackActionProcessor.class);
        DoNotTraceCallback.setCallbackActionProcessor(callbackActionProcessor);

        runTest("DoNotTraceIn");
        verify(callbackActionProcessor).startDoNotTrace();
        verify(callbackActionProcessor).endDoNotTrace();
    }

}
