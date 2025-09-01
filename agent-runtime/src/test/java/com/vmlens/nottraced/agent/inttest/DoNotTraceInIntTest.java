package com.vmlens.nottraced.agent.inttest;

import com.vmlens.trace.agent.bootstrap.callback.DoNotTraceCallback;
import com.vmlens.trace.agent.bootstrap.callback.impl.DoNotTraceCallbackImpl;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class DoNotTraceInIntTest extends AbstractIntTest  {

    @Test
    public void whenDoNotTraceApplies_thenDoNotTraceGetCalled() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        DoNotTraceCallbackImpl doNotTraceCallbackMock = mock(DoNotTraceCallbackImpl.class);
        DoNotTraceCallback.setDoNotTraceCallbackImpl(doNotTraceCallbackMock);

        runTest("DoNotTraceIn");
        verify(doNotTraceCallbackMock, times(1)).methodEnter(any(), anyInt());
        verify(doNotTraceCallbackMock, times(1)).methodExit(any(), anyInt());
    }

}
