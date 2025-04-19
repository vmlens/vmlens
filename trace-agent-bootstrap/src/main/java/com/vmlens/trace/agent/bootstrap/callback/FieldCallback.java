package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.impl.FieldCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositorySingleton;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.*;


public class FieldCallback {

    private static volatile FieldCallbackImpl fieldCallbackImpl = new FieldCallbackImpl(
            FieldRepositorySingleton.INSTANCE,
            new ThreadLocalWhenInTestAdapterImpl(),
            ReadWriteLockMap.INSTANCE);

    public static void beforeFieldRead(Object fromObject, int fieldId, int position, int inMethodId) {
        if(canProcess()) {
            startProcess();
            try {
                fieldCallbackImpl.beforeFieldRead(fromObject, fieldId, position, inMethodId);
            } finally {
                stopProcess();
            }
        }
    }

    public static void beforeFieldWrite(Object fromObject, int fieldId, int position, int inMethodId) {
        if(canProcess()) {
            startProcess();
            try {
                fieldCallbackImpl.beforeFieldWrite(fromObject, fieldId, position, inMethodId);
            } finally {
                stopProcess();
            }
        }
    }

    public static void beforeStaticFieldRead(int fieldId, int position, int inMethodId) {
        if(canProcess()) {
            startProcess();
            try {
                fieldCallbackImpl.beforeStaticFieldRead(fieldId, position, inMethodId);
            } finally {
                stopProcess();
            }
        }
    }


    public static void beforeStaticFieldWrite(int fieldId, int position, int inMethodId) {
        if(canProcess()) {
            startProcess();
            try {
                fieldCallbackImpl.beforeStaticFieldWrite(fieldId, position, inMethodId);
            } finally {
                stopProcess();
            }
        }
    }

    public static void afterFieldAccess() {
        if(canProcess()) {
            startProcess();
            try {
                fieldCallbackImpl.afterFieldAccess();
            } finally {
                stopProcess();
            }
        }
    }

    public static void setFieldCallbackImpl(FieldCallbackImpl fieldCallbackImpl) {
        FieldCallback.fieldCallbackImpl = fieldCallbackImpl;
    }
}
