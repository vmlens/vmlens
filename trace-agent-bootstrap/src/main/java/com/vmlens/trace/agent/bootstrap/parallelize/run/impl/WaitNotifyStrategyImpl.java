package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class WaitNotifyStrategyImpl implements WaitNotifyStrategy {

    public WaitNotifyStrategyImpl() {
    }

    /* To avoid that the waiting thread is blocking the active thread trying to get a log the time is exponential

    "Thread-29" #49 prio=5 os_prio=31 cpu=9,28ms elapsed=0,36s tid=0x00000001289bcc00 nid=0x8f07 waiting on condition  [0x000000017882a000]
   java.lang.Thread.State: WAITING (parking)
	at jdk.internal.misc.Unsafe.park(java.base@17.0.14/Native Method)
	- parking to wait for  <0x0000000782773178> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
	at java.util.concurrent.locks.LockSupport.park(java.base@17.0.14/LockSupport.java:211)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(java.base@17.0.14/AbstractQueuedSynchronizer.java:715)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@17.0.14/AbstractQueuedSynchronizer.java:1767)
	at com.vmlens.transformed.agent.bootstrap.parallelize.run.impl.WaitNotifyStrategyImpl.notifyAndWaitTillActive(WaitNotifyStrategyImpl.java:24)
	at com.vmlens.transformed.agent.bootstrap.parallelize.run.impl.RunImpl.newTask(RunImpl.java:68)
	at com.vmlens.transformed.agent.bootstrap.parallelize.loop.ParallelizeLoop.newTask(ParallelizeLoop.java:51)
	at com.vmlens.transformed.agent.bootstrap.parallelize.facade.ParallelizeFacade.newTask(ParallelizeFacade.java:34)
	at com.vmlens.transformed.agent.bootstrap.callback.callbackaction.CallbackActionProcessorImpl.process(CallbackActionProcessorImpl.java:98)
	at com.vmlens.transformed.agent.bootstrap.callback.callbackaction.CallbackActionProcessorImpl.processWithCheckNewThread(CallbackActionProcessorImpl.java:75)
	at com.vmlens.transformed.agent.bootstrap.callback.MethodCallback.methodEnter(MethodCallback.java:37)
	at org.h2.test.db.TestMultiThread.run(TestMultiThread.java)
	at java.lang.Thread.run(java.base@17.0.14/Thread.java:840)

"Thread-30" #50 prio=5 os_prio=31 cpu=13,31ms elapsed=0,34s tid=0x0000000127e9be00 nid=0x8e23 runnable  [0x0000000178a36000]
   java.lang.Thread.State: RUNNABLE
	at java.lang.Thread.dumpThreads(java.base@17.0.14/Native Method)
	at java.lang.Thread.getStackTrace(java.base@17.0.14/Thread.java:1610)
	at com.vmlens.transformed.agent.bootstrap.parallelize.run.thread.ThreadForParallelize.isBlocked(ThreadForParallelize.java:77)
	at com.vmlens.transformed.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap.isActive(ThreadIndexAndThreadStateMap.java:90)
	at com.vmlens.transformed.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap.getActiveThreadIndices(ThreadIndexAndThreadStateMap.java:82)
	at com.vmlens.transformed.agent.bootstrap.parallelize.run.impl.RunStateContext.isActive(RunStateContext.java:24)
	at com.vmlens.transformed.agent.bootstrap.parallelize.run.impl.runstate.RunStateActive.calculateIsActive(RunStateActive.java:28)
	at com.vmlens.transformed.agent.bootstrap.parallelize.run.impl.runstate.RunStateActive.isActive(RunStateActive.java:34)
	at com.vmlens.transformed.agent.bootstrap.parallelize.run.impl.RunStateMachineImpl.isActive(RunStateMachineImpl.java:47)
	at com.vmlens.transformed.agent.bootstrap.parallelize.run.impl.WaitNotifyStrategyImpl.notifyAndWaitTillActive(WaitNotifyStrategyImpl.java:23)
	at com.vmlens.transformed.agent.bootstrap.parallelize.run.impl.RunImpl.newTask(RunImpl.java:68)
	at com.vmlens.transformed.agent.bootstrap.parallelize.loop.ParallelizeLoop.newTask(ParallelizeLoop.java:51)
	at com.vmlens.transformed.agent.bootstrap.parallelize.facade.ParallelizeFacade.newTask(ParallelizeFacade.java:34)
	at com.vmlens.transformed.agent.bootstrap.callback.callbackaction.CallbackActionProcessorImpl.process(CallbackActionProcessorImpl.java:98)
	at com.vmlens.transformed.agent.bootstrap.callback.callbackaction.CallbackActionProcessorImpl.processWithCheckNewThread(CallbackActionProcessorImpl.java:7
     *
      improved the time from 44.17  to 16.30 s

     we use TimeUnit.NANOSECONDS since in wait it is transformed to nanoseconds anyway
     */
    private static final long[] waitTimesNs = new long[] {
            1_000L,        // 1 µs
            2_000L,        // 2 µs
            4_000L,        // 4 µs
            8_000L,        // 8 µs
            16_000L,       // 16 µs
            32_000L,       // 32 µs
            64_000L,       // 64 µs
            128_000L,      // 128 µs
            256_000L,      // 256 µs
            512_000L,      // 512 µs
            1_024_000L,    // 1.024 ms
            2_048_000L,    // 2.048 ms
            4_096_000L,    // 4.096 ms
            8_192_000L,    // 8.192 ms
            16_384_000L,   // 16.384 ms
            32_768_000L,   // 32.768 ms
            65_536_000L,   // 65.536 ms
            131_072_000L,  // 131.072 ms
            262_144_000L   // 262.144 ms
    };

    @Override
    public void notifyAndWaitTillActive(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                                        RunStateMachine runStateMachine,
                                        Condition threadActiveCondition,
                                        SendEvent sendEvent) {
        try {
            threadActiveCondition.signalAll();
            int index = 0;
            while (!runStateMachine.isActive(threadLocalDataWhenInTest,sendEvent)) {
                threadActiveCondition.await(waitTimesNs[index], TimeUnit.NANOSECONDS);
                if(runStateMachine.checkStopWaiting(sendEvent,threadLocalDataWhenInTest.threadIndex())) {
                    return;
                }
                if((index+1) < waitTimesNs.length) {
                    index++;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
