package com.vmlens.trace.agent.bootstrap.callbackdeprecated;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ParallelizeBridgeForCallbackImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class LockTemplateCallback {


    public static void conditionAwait(AbstractQueuedSynchronizer sync) {


        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();
        access(sync, -1, false, false);
        access(sync, -1, true, false);


    }


    public static boolean tryLock(ReentrantLock lock, int methodId) {
        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();

        try {


            if (lock.tryLock()) {

                access(getSync(callbackStatePerThread, lock), methodId, true, false);
                return true;
            } else {
                return false;
            }

        } finally {

        }


    }


    public static boolean tryLock(ReentrantLock lock, long time, TimeUnit unit, int methodId) throws InterruptedException {
        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();


        if (lock.tryLock(time, unit)) {

                access(getSync(callbackStatePerThread, lock), methodId, true, false);
                return true;
            } else {
                return false;
            }



    }


    public static void lockInterruptibly(ReentrantLock lock, int methodId) throws InterruptedException {
        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();


            lock.lockInterruptibly();
            access(getSync(callbackStatePerThread, lock), methodId, true, false);



    }


    public static void lock(ReentrantLock lock, int methodId) {
        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();

            lock.lock();



        access(getSync(callbackStatePerThread, lock), methodId, true, false);

    }

    public static void unlock(ReentrantLock lock, int methodId) {

        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();

        lock.unlock();


        access(getSync(callbackStatePerThread, lock), methodId, false, false);


    }


    public static boolean tryLock(ReadLock lock, int methodId) {
        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();


        if (lock.tryLock()) {

                access(getSync(callbackStatePerThread, lock), methodId, true, true);
                return true;
            } else {
                return false;
            }


    }

    public static boolean tryLock(ReadLock lock, long time, TimeUnit unit, int methodId) throws InterruptedException {
        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();


        if (lock.tryLock(time, unit)) {

                access(getSync(callbackStatePerThread, lock), methodId, true, true);
                return true;
            } else {
                return false;
            }


    }

    public static void lockInterruptibly(ReadLock lock, int methodId) throws InterruptedException {
        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();


        lock.lockInterruptibly();

        access(getSync(callbackStatePerThread, lock), methodId, true, true);

    }


    public static void lock(ReadLock lock, int methodId) {
        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();

        lock.lock();


        access(getSync(callbackStatePerThread, lock), methodId, true, true);

    }

    public static void unlock(ReadLock lock, int methodId) {
        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();


        lock.unlock();


        access(getSync(callbackStatePerThread, lock), methodId, false, true);


    }


    public static boolean tryLock(WriteLock lock, int methodId) {
        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();

            if (lock.tryLock()) {

                access(getSync(callbackStatePerThread, lock), methodId, true, false);
                return true;
            } else {
                return false;
            }


    }


    public static boolean tryLock(WriteLock lock, long time, TimeUnit unit, int methodId) throws InterruptedException {
        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();

            if (lock.tryLock(time, unit)) {

                access(getSync(callbackStatePerThread, lock), methodId, true, false);
                return true;
            } else {
                return false;
            }


    }


    public static void lockInterruptibly(WriteLock lock, int methodId) throws InterruptedException {
        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();


        lock.lockInterruptibly();


        access(getSync(callbackStatePerThread, lock), methodId, true, false);

    }


    public static void lock(WriteLock lock, int methodId) {
        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();

        lock.lock();


        access(getSync(callbackStatePerThread, lock), methodId, true, false);

    }

    public static void unlock(WriteLock lock, int methodId) {
        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();

        lock.unlock();



        access(getSync(callbackStatePerThread, lock), methodId, false, false);


    }


    public static boolean tryLock(Lock lock, int methodId) {

        if (lock instanceof ReentrantLock) {
            return tryLock((ReentrantLock) lock, methodId);
        } else if (lock instanceof ReadLock) {
            return tryLock((ReadLock) lock, methodId);
        } else if (lock instanceof WriteLock) {
            return tryLock((WriteLock) lock, methodId);
        } else {
            return lock.tryLock();
        }


    }


    public static boolean tryLock(Lock lock, long time,
                                  TimeUnit unit, int methodId) throws InterruptedException {

        if (lock instanceof ReentrantLock) {
            return tryLock((ReentrantLock) lock, time, unit, methodId);
        } else if (lock instanceof ReadLock) {
            return tryLock((ReadLock) lock, time, unit, methodId);
        } else if (lock instanceof WriteLock) {
            return tryLock((WriteLock) lock, time, unit, methodId);
        } else {
            return lock.tryLock(time, unit);
        }


    }

    public static void lockInterruptibly(Lock lock, int methodId) throws InterruptedException {

        if (lock instanceof ReentrantLock) {
            lockInterruptibly((ReentrantLock) lock, methodId);
        } else if (lock instanceof ReadLock) {
            lockInterruptibly((ReadLock) lock, methodId);
        } else if (lock instanceof WriteLock) {
            lockInterruptibly((WriteLock) lock, methodId);
        } else {
            lock.lockInterruptibly();
        }


    }


    public static void lock(Lock lock, int methodId) {

        if (lock instanceof ReentrantLock) {
            lock((ReentrantLock) lock, methodId);
        } else if (lock instanceof ReadLock) {
            lock((ReadLock) lock, methodId);
        } else if (lock instanceof WriteLock) {
            lock((WriteLock) lock, methodId);
        } else {
            lock.lock();
        }


    }

    public static void unlock(Lock lock, int methodId) {

        if (lock instanceof ReentrantLock) {
            unlock((ReentrantLock) lock, methodId);
        } else if (lock instanceof ReadLock) {
            unlock((ReadLock) lock, methodId);
        } else if (lock instanceof WriteLock) {
            unlock((WriteLock) lock, methodId);
        } else {
            lock.unlock();
        }


    }


    private static Object getSync(ThreadLocalForParallelize callbackStatePerThread, Object theLock) {
        try {
            Field f = theLock.getClass().getDeclaredField("sync");
            f.setAccessible(true);
            return f.get(theLock);


        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);

        }
    }


    protected static void access(Object theSync, int methodId, boolean isLockEnter, boolean isShared) {
        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();
        // Fixme Callback
        //  ParallelizeFacade.afterLockOperation(callbackStatePerThread, new ParallelizeLock(isLockEnter, isShared, System.identityHashCode(theSync)));
        // callbackStatePerThread.programCount++;
    }
}
