package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.impl.ArrayCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;

public class ArrayCallback {

    private static volatile ArrayCallbackImpl arrayCallbackImpl = new ArrayCallbackImpl(
            new ThreadLocalWhenInTestAdapterImpl());

    public static void beforeArrayRead(Object array, int index, int inMethodId) {
        arrayCallbackImpl.beforeArrayRead(array,index,inMethodId);
    }

    public static void beforeArrayWrite(Object array, int index, int inMethodId) {
        arrayCallbackImpl.beforeArrayWrite(array,index,inMethodId);
    }

    public static void arraySetLong(long[] array, int index, long value, int inMethodId) {
        arrayCallbackImpl.beforeArrayWrite(array,index,inMethodId);
        array[index] = value;
    }

    public static void arraySetDouble(double[] array, int index, double value, int inMethodId) {
        arrayCallbackImpl.beforeArrayWrite(array,index,inMethodId);
        array[index] = value;
    }

    // For Test
    public static void setArrayCallbackImpl(ArrayCallbackImpl arrayCallbackImpl) {
        ArrayCallback.arrayCallbackImpl = arrayCallbackImpl;
    }
}
