package com.vmlens.nottraced.agent.inttest;

import com.vmlens.trace.agent.bootstrap.callback.MethodCallback;
import com.vmlens.trace.agent.bootstrap.callback.impl.MethodCallbackImpl;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class OldBytecodeTest extends AbstractIntTest {

    /**
     *   org.apache.commons.lang.StringUtils from commons-lang 1.0 is
     *   class version 45.3 (196653)
     *   e.g. jdk 1.1
     */
    @Test
    public void oldByteCodeForExampleStringUtilsCanBeTransformed() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        MethodCallbackImpl methodCallbackImplMock = mock(MethodCallbackImpl.class);
        MethodCallback.setMethodCallbackImpl(methodCallbackImplMock);

        runTest("CallApacheStringUtil");
        verify(methodCallbackImplMock, times(2)).methodEnter(any(), anyInt());
        verify(methodCallbackImplMock, times(2)).methodExit(any(), anyInt());
    }


}
