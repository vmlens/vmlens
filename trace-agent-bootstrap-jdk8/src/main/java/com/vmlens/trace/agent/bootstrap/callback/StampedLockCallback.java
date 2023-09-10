package com.vmlens.trace.agent.bootstrap.callback;


import com.vmlens.trace.agent.bootstrap.callback.state.LockIdAndOrder;
import com.vmlens.trace.agent.bootstrap.util.Constants;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.locks.StampedLock;

public class StampedLockCallback {

    private static final AnarsoftWeakHashMap<StampedLockType> stampedLock2Type =
            new AnarsoftWeakHashMap<StampedLockType>();
    private static final Object LOCK = new Object();


    private static void setType(Object lock, StampedLockType stampedLockType) {
        synchronized (LOCK) {
            stampedLock2Type.put(lock, stampedLockType);
        }
    }


    public static long readLock(StampedLock lock, int methodId) {

        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        callbackStatePerThread.stackTraceBasedDoNotTrace++;
        try {
            return lock.readLock();

        } finally {
            callbackStatePerThread.stackTraceBasedDoNotTrace--;
            setType(lock, StampedLockType.SHARED);
            access(callbackStatePerThread, lock, methodId, true, true, Constants.STAMPED_READ_LOCK);

        }


    }

    public static void unlockRead(StampedLock lock, long stamp, int methodId) {

        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        callbackStatePerThread.stackTraceBasedDoNotTrace++;
        try {
            lock.unlockRead(stamp);

        } finally {
            callbackStatePerThread.stackTraceBasedDoNotTrace--;
        }

        access(callbackStatePerThread, lock, methodId, false, true, Constants.STAMPED_READ_UNLOCK);


    }


    public static long writeLock(StampedLock lock, int methodId) {

        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        callbackStatePerThread.stackTraceBasedDoNotTrace++;
        try {
            return lock.writeLock();

        } finally {
            callbackStatePerThread.stackTraceBasedDoNotTrace--;
            setType(lock, StampedLockType.EXCLUSIVE);
            access(callbackStatePerThread, lock, methodId, true, false, Constants.STAMPED_WRITE_LOCK);

        }


    }

    public static void unlockWrite(StampedLock lock, long stamp, int methodId) {

        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        callbackStatePerThread.stackTraceBasedDoNotTrace++;
        try {
            lock.unlockWrite(stamp);

        } finally {
            callbackStatePerThread.stackTraceBasedDoNotTrace--;
        }

        access(callbackStatePerThread, lock, methodId, false, false, Constants.STAMPED_WRITE_UNLOCK);


    }


    public static void unlock(StampedLock lock, long stamp, int methodId) {

        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        callbackStatePerThread.stackTraceBasedDoNotTrace++;
        try {
            lock.unlock(stamp);

        } finally {
            callbackStatePerThread.stackTraceBasedDoNotTrace--;
        }

        StampedLockType stampedLockType = StampedLockType.NONE;

        synchronized (LOCK) {
            StampedLockType n = stampedLock2Type.get(lock);

            if (n != null) {
                stampedLockType = n;
            }


        }

        switch (stampedLockType) {
            case NONE:
                break;

            case EXCLUSIVE:
                access(callbackStatePerThread, lock, methodId, false, false, Constants.STAMPED_UNLOCK);
                break;
            case SHARED:
                access(callbackStatePerThread, lock, methodId, false, true, Constants.STAMPED_UNLOCK);
                break;
        }

    }


    private static void call(StampedLock theLock, String name) {

        try {

            Method m = theLock.getClass().getDeclaredMethod(name);
            m.setAccessible(true);
            m.invoke(theLock);


        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 SecurityException e) {
            throw new RuntimeException(e);
        }


    }


    public static void unstampedUnlockWrite(StampedLock lock, int methodId) {
        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        callbackStatePerThread.stackTraceBasedDoNotTrace++;
        try {
            call(lock, "unstampedUnlockWrite");

        } finally {
            callbackStatePerThread.stackTraceBasedDoNotTrace--;
        }

        access(callbackStatePerThread, lock, methodId, false, false, Constants.STAMPED_PRIVATE);
    }


    public static void unstampedUnlockRead(StampedLock lock, int methodId) {
        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        callbackStatePerThread.stackTraceBasedDoNotTrace++;
        try {
            call(lock, "unstampedUnlockRead");

        } finally {
            callbackStatePerThread.stackTraceBasedDoNotTrace--;
        }

        access(callbackStatePerThread, lock, methodId, false, true, Constants.STAMPED_PRIVATE);
    }


    protected static void access(CallbackStatePerThread callbackStatePerThread, Object theLock, int methodId, boolean isLockEnter, boolean isShared, int stampedLockMethodId) {
        int slidingWindowId = CallbackState.traceSyncStatements(callbackStatePerThread);


        if (!CallbackState.isSlidingWindowTrace(slidingWindowId)) {
            return;
        }


        callbackStatePerThread.programCount++;
        int currentProgramCounter = callbackStatePerThread.programCount;

        int order = 0;
        LockIdAndOrder current = null;

        synchronized (LockStatementCallback.objectToOrder) {
            current = LockStatementCallback.objectToOrder.get(theLock);

            if (current == null) {
                current = new LockIdAndOrder();
                current.id = LockIdAndOrder.getNewId();
            }

            order = current.order;
            current.order = current.order + 1;
            LockStatementCallback.objectToOrder.put(theLock, current);

        }

        // Fixme Callback
//        if (isLockEnter) {
//            callbackStatePerThread.sendEvent.writeStampedLockEnterEventGen(slidingWindowId, currentProgramCounter, order,
//                    current.id, callbackStatePerThread.methodCount, isShared, 4, stampedLockMethodId);
//
//        } else {
//            callbackStatePerThread.sendEvent.writeStampedLockExitEventGen(slidingWindowId, callbackStatePerThread.programCount, order, current.id, callbackStatePerThread.methodCount, isShared, 4, stampedLockMethodId);
//        }
//        ParallelizeFacade.afterLockOperation(callbackStatePerThread, new ParallelizeLock(isLockEnter, isShared, current.id));


        callbackStatePerThread.programCount++;
    }

}
