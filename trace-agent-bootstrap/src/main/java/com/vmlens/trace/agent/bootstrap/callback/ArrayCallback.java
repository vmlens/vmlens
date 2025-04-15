package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.impl.ArrayCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.*;

public class ArrayCallback {

    private static volatile ArrayCallbackImpl arrayCallbackImpl = new ArrayCallbackImpl(
            new ThreadLocalWhenInTestAdapterImpl());

    public static void beforeArrayRead(Object array, int index, int inMethodId) {
        if(canProcess()) {
            startProcess();
            try {
                arrayCallbackImpl.beforeArrayRead(array, index, inMethodId);
            } finally {
                stopProcess();
            }
        }
    }

    public static void beforeArrayWrite(Object array, int index, int inMethodId) {
       if(canProcess()) {
           startProcess();
           try {
               arrayCallbackImpl.beforeArrayWrite(array,index,inMethodId);
           }
           finally {
               stopProcess();
           }
       }
    }

    public static void arraySetLong(long[] array, int index, long value, int inMethodId) {
        array[index] = value;
        if(canProcess()) {
            startProcess();
            try {
                arrayCallbackImpl.beforeArrayWrite(array,index,inMethodId);
            }
            finally {
                stopProcess();
            }
        }
    }

    public static void arraySetDouble(double[] array, int index, double value, int inMethodId) {
        array[index] = value;
        if(canProcess()) {
            startProcess();
            try {
                arrayCallbackImpl.beforeArrayWrite(array,index,inMethodId);
            }
            finally {
                stopProcess();
            }
        }
    }

    // For Test
    public static void setArrayCallbackImpl(ArrayCallbackImpl arrayCallbackImpl) {
        ArrayCallback.arrayCallbackImpl = arrayCallbackImpl;
    }
}
