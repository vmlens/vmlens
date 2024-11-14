package com.vmlens.trace.agent.bootstrap.callbackdeprecated;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ParallelizeBridgeForCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.state.MonitorIdAndOrder;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import gnu.trove.map.hash.TIntIntHashMap;

public class SynchronizedStatementCallbackImpl {
    private final AnarsoftWeakHashMap<MonitorIdAndOrder> objectToOrder = new AnarsoftWeakHashMap<MonitorIdAndOrder>();
    private final TIntIntHashMap staticIdToOrder = new TIntIntHashMap();

    public void waitCall(Object in, int methodId) throws InterruptedException {
        try {
            monitorExit(in, methodId, -2);
            in.wait();
        } finally {
            monitorEnter(in, methodId, -1);
        }

    }

    public void waitCall(Object in, long timeout, int methodId) throws InterruptedException {
        try {
            monitorExit(in, methodId, -2);
            in.wait(timeout);
        } finally {
            monitorEnter(in, methodId, -1);
        }
    }

    public void waitCall(Object in, long timeout, int nanos, int methodId) throws InterruptedException {
        try {
            monitorExit(in, methodId, -2);
            in.wait(timeout, nanos);
        } finally {
            monitorEnter(in, methodId, -1);
        }
    }

    public void synchronizedMethod(Object objectKey, int methodId) {
        try {
            ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();
            monitorEnterInternal(objectKey, methodId, 0);

        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void staticSynchronizedMethod(int id, int methodId) {
     /*   try {
            CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            int slidingWindowId = CallbackState.traceSyncStatements(callbackStatePerThread);
            if (CallbackState.isSlidingWindowTrace(slidingWindowId)) {
                monitorEnterStaticInternal(id, methodId, callbackStatePerThread, 0, slidingWindowId);
            }
        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }*/
    }

    public void synchronizedMethodExit(Object objectKey, int methodId) {
        try {
            monitorExit(objectKey, methodId, -1);
        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void staticSynchronizedMethodExit(int id, int methodId) {
     /*   try {
            CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            int slidingWindowId = CallbackState.traceSyncStatements(callbackStatePerThread);
            if (CallbackState.isSlidingWindowTrace(slidingWindowId)) {
                monitorExitStatic_internal(id, methodId, callbackStatePerThread, slidingWindowId);
            }
        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }*/
    }

    public void monitorEnter(Object objectKey, int methodId, int position) {
        try {
            monitorEnterInternal(objectKey, methodId, position);
        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void monitorExit(Object objectKey, int methodId, int position) {
     /*   try {
            CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
            int slidingWindowId = CallbackState.traceSyncStatements(callbackStatePerThread);
            if (CallbackState.isSlidingWindowTrace(slidingWindowId)) {
                if (objectKey instanceof java.lang.Class) {
                    String className = ((java.lang.Class) objectKey).getName().replace('.', '/');
                    int id = StaticMonitorRepository.getOrCreate(className);
                    monitorExitStatic_internal(id, methodId, callbackStatePerThread, slidingWindowId);
                    return;
                }
                int order = 0;
                MonitorIdAndOrder current = null;
                synchronized (objectToOrder) {
                    current = objectToOrder.get(objectKey);
                    if (current == null) {
                        current = new MonitorIdAndOrder();
                        current.id = MonitorIdAndOrder.getNewId();
                    }
                    order = current.order;
                    current.order = current.order + 1;
                    objectToOrder.put(objectKey, current);
                }
                callbackStatePerThread.programCount++;
                // Fixme Callback
//                callbackStatePerThread.sendEvent.writeMonitorExitEventGen(slidingWindowId,
//                        callbackStatePerThread.programCount, order, current.id, callbackStatePerThread.methodCount,
//                        methodId, position);
//                callbackStatePerThread.programCount++;
//                ParallelizeFacade.beforeMonitorExit(callbackStatePerThread, current.id, methodId, position);
            }
        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        } */
    }

    private void monitorEnterInternal(Object objectKey, int methodId, int position) {
    /*    CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        int slidingWindowId = CallbackState.traceSyncStatements(callbackStatePerThread);
        if (!CallbackState.isSlidingWindowTrace(slidingWindowId)) {
            return;
        }
        if (objectKey instanceof java.lang.Class) {
            String className = ((java.lang.Class) objectKey).getName().replace('.', '/');
            int id = StaticMonitorRepository.getOrCreate(className);
            monitorEnterStaticInternal(id, methodId, callbackStatePerThread, position, slidingWindowId);
            return;
        }
        callbackStatePerThread.programCount++;
        int currentProgramCounter = callbackStatePerThread.programCount;
        int order = 0;
        MonitorIdAndOrder current = null;
        synchronized (objectToOrder) {
            current = objectToOrder.get(objectKey);
            if (current == null) {
                current = new MonitorIdAndOrder();
                current.id = MonitorIdAndOrder.getNewId();
            }
            order = current.order;
            current.order = current.order + 1;
            objectToOrder.put(objectKey, current);
        } */
// Fixme Callback
//        callbackStatePerThread.sendEvent.writeMonitorEnterEventGen(slidingWindowId, currentProgramCounter, order,
//                current.id, callbackStatePerThread.methodCount, methodId, position);
//        callbackStatePerThread.programCount++;
//        ParallelizeFacade.onMonitorEnter(callbackStatePerThread, current.id);
    }

    private void monitorEnterStaticInternal(int id, int methodId, ThreadLocalForParallelize callbackStatePerThread,
                                            int position, int slidingWindowId) {
      /*  callbackStatePerThread.programCount++;
        int currentProgramCounter = callbackStatePerThread.programCount;
        int order = 0;
        synchronized (staticIdToOrder) {
            if (staticIdToOrder.contains(id)) {
                order = staticIdToOrder.get(id);
            }
            staticIdToOrder.put(id, order + 1);
        }
        if (id * -1 > 0) {
            System.out.println("wrong id " + id);
        } */
        // Fixme Callback
//        callbackStatePerThread.sendEvent.writeMonitorEnterEventGen(slidingWindowId, currentProgramCounter, order,
//                id * -1, callbackStatePerThread.methodCount, methodId, position);
//        callbackStatePerThread.programCount++;
//        ParallelizeFacade.onMonitorEnter(callbackStatePerThread, id * -1);
    }

    private void monitorExitStatic_internal(int id, int methodId, ThreadLocalForParallelize callbackStatePerThread,
                                            int slidingWindowId) {
        // Fixme Callback
//        ParallelizeFacade.beforeMonitorExitStatic(callbackStatePerThread, slidingWindowId, methodId);
        int order = 0;
        synchronized (staticIdToOrder) {
            if (staticIdToOrder.contains(id)) {
                order = staticIdToOrder.get(id);
            }
            staticIdToOrder.put(id, order + 1);
        }
        if (id * -1 > 0) {
            System.out.println("wrong id " + id);
        }
        //       callbackStatePerThread.programCount++;
        // Fixme Callback
//        callbackStatePerThread.sendEvent.writeMonitorExitEventGen(slidingWindowId, callbackStatePerThread.programCount,
//                order, id * -1, callbackStatePerThread.methodCount, methodId, 0);
        //     callbackStatePerThread.programCount++;
    }
}
