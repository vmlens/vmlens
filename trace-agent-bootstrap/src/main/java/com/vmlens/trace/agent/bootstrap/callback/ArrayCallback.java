package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessorImpl;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.nomethodaction.ArrayAccessAction;

public class ArrayCallback {

    private static volatile CallbackActionProcessor callbackActionProcessor = new CallbackActionProcessorImpl();

    public static void beforeArrayRead(Object array, int index, int inMethodId) {
        callbackActionProcessor.process(new ArrayAccessAction(array,index,inMethodId,MemoryAccessType.IS_READ));
    }

    public static void beforeArrayWrite(Object array, int index, int inMethodId) {
        callbackActionProcessor.process(new ArrayAccessAction(array,index,inMethodId,MemoryAccessType.IS_WRITE));
    }

    public static void arraySetLong(long[] array, int index, long value, int inMethodId) {
        array[index] = value;
        callbackActionProcessor.process(new ArrayAccessAction(array,index,inMethodId,MemoryAccessType.IS_WRITE));
    }

    public static void arraySetDouble(double[] array, int index, double value, int inMethodId) {
        array[index] = value;
        callbackActionProcessor.process(new ArrayAccessAction(array,index,inMethodId,MemoryAccessType.IS_WRITE));
    }

    // For Test
    public static void setCallbackActionProcessor(CallbackActionProcessor callbackActionProcessor) {
        ArrayCallback.callbackActionProcessor = callbackActionProcessor;
    }
}
