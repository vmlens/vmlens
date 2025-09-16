package com.vmlens.nottraced.agent.inttest;

import com.vmlens.trace.agent.bootstrap.callback.MethodCallback;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.impl.MethodEnterAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.impl.MethodExitAction;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OldBytecodeTest extends AbstractIntTest {

    /**
     *   org.apache.commons.lang.StringUtils from commons-lang 1.0 is
     *   class version 45.3 (196653)
     *   e.g. jdk 1.1
     */
    @Test
    public void oldByteCodeForExampleStringUtilsCanBeTransformed() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        CallbackActionProcessorMock callbackActionProcessor = new CallbackActionProcessorMock();
        MethodCallback.setCallbackActionProcessor(callbackActionProcessor);

        runTest("CallApacheStringUtil");
        assertThat(callbackActionProcessor.getCount(MethodEnterAction.class), is(2));
        assertThat(callbackActionProcessor.getCount(MethodExitAction.class), is(2));
    }


}
