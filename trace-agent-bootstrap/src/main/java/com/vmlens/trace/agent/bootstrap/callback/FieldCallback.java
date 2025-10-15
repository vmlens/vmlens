package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessorImpl;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.nomethodaction.AfterFieldAccessAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.nomethodaction.BeforeFieldAccessAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.nomethodaction.BeforeStaticFieldAccessAction;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryForCallback;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositorySingleton;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;


public class FieldCallback {

    private static volatile CallbackActionProcessor callbackActionProcessor = new CallbackActionProcessorImpl();
    private static final FieldRepositoryForCallback fieldIdToStrategy =  FieldRepositorySingleton.INSTANCE;
    private static final ReadWriteLockMap readWriteLockMap =  ReadWriteLockMap.INSTANCE;

    public static void beforeFieldRead(Object fromObject, int fieldId, int position, int inMethodId) {
        callbackActionProcessor.process(new BeforeFieldAccessAction(fieldIdToStrategy,
                fromObject,  fieldId,  position,  inMethodId, MemoryAccessType.IS_READ));
    }

    public static void beforeFieldWrite(Object fromObject, int fieldId, int position, int inMethodId) {
        callbackActionProcessor.process(new BeforeFieldAccessAction(fieldIdToStrategy,
                fromObject,  fieldId,  position,  inMethodId, MemoryAccessType.IS_WRITE));
    }

    public static void beforeStaticFieldRead(int fieldId, int position, int inMethodId) {
        callbackActionProcessor.process(new BeforeStaticFieldAccessAction(fieldIdToStrategy,
                 fieldId,  position,  inMethodId, MemoryAccessType.IS_READ));
    }


    public static void beforeStaticFieldWrite(int fieldId, int position, int inMethodId) {
        callbackActionProcessor.process(new BeforeStaticFieldAccessAction(fieldIdToStrategy,
                fieldId,  position,  inMethodId, MemoryAccessType.IS_WRITE));
    }

    public static void afterFieldAccess() {
        callbackActionProcessor.process(new AfterFieldAccessAction(readWriteLockMap));
    }

    public static void setCallbackActionProcessor(CallbackActionProcessor callbackActionProcessor) {
        FieldCallback.callbackActionProcessor = callbackActionProcessor;
    }
}
