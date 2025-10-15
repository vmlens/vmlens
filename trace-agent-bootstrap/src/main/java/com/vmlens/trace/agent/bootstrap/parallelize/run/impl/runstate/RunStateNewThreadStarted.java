package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.ExecuteBeforeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.NewTaskContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.AfterContextForStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateAndResult;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate.ProcessAfter.process;


public class RunStateNewThreadStarted implements RunState {


    private final RunStateContext runStateContext;
    private final ThreadWrapper startedThread;
    private final int threadIndexForNewTestTask;
    private final RunState previousState;
    private boolean startedThreadRunning;

    public RunStateNewThreadStarted(RunStateContext runStateContext,
                                    ThreadWrapper startedThread,
                                    int threadIndexForNewTestTask,
                                    RunState previousState) {
        this.runStateContext = runStateContext;
        this.startedThread = startedThread;
        this.threadIndexForNewTestTask = threadIndexForNewTestTask;
        this.previousState = previousState;
    }

    @Override
    public boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,
                            SendEvent sendEvent) {
        if(threadLocalDataWhenInTest.threadIndex() == threadIndexForNewTestTask) {
            startedThreadRunning = true;
        }
        return false;
    }

    @Override
    public RunStateAndChangeFlag after(AfterContextForStateMachine afterContext, SendEvent sendEvent) {
        boolean change = process(afterContext, sendEvent, runStateContext);
        return new RunStateAndChangeFlag(this, change);
    }

    @Override
    public RunStateAndResult<ThreadLocalWhenInTest> processNewTestTask(NewTaskContext newTaskContext,
                       Run run,
                       SendEvent sendEvent) {
        if (!startedThread.equals(newTaskContext.newThread())) {
            return RunStateAndResult.of(this, null);
        }
        ThreadLocalWhenInTest threadLocalDataWhenInTest = runStateContext.createForStartedThread(
                run, newTaskContext.threadLocalForParallelize(), threadIndexForNewTestTask, sendEvent,newTaskContext.firstMethodInThread());
        newTaskContext.threadLocalForParallelize().setThreadLocalDataWhenInTest(threadLocalDataWhenInTest);

        return RunStateAndResult.of(previousState,threadLocalDataWhenInTest);
    }

    @Override
    public RunStateAndResult<Boolean> checkBlocked(SendEvent sendEvent) {
        /*
         * we need to wait that the new thread was really started,
         * checking for blocked leads to failing tests
         * Perhaps because the thread does yet exist when the checkBlocked gets called
         *
         * But we also have this:
         *
    "Thread-15" #51 [35643] prio=5 os_prio=31 cpu=0.21ms elapsed=25.41s tid=0x00000001308d5a00 nid=35643 waiting on condition  [0x000000016f262000]
   java.lang.Thread.State: WAITING (parking)
	at jdk.internal.misc.Unsafe.park(java.base@24.0.1/Native Method)
	- parking to wait for  <0x0000000782950080> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(java.base@24.0.1/LockSupport.java:369)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionNode.block(java.base@24.0.1/AbstractQueuedSynchronizer.java:519)
	at java.util.concurrent.ForkJoinPool.unmanagedBlock(java.base@24.0.1/ForkJoinPool.java:3945)
	at java.util.concurrent.ForkJoinPool.managedBlock(java.base@24.0.1/ForkJoinPool.java:3891)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@24.0.1/AbstractQueuedSynchronizer.java:1751)
	at com.vmlens.transformed.agent.bootstrap.parallelize.run.impl.WaitNotifyStrategyImpl.notifyAndWaitTillActive(WaitNotifyStrategyImpl.java:25)
	at com.vmlens.transformed.agent.bootstrap.parallelize.run.impl.RunImpl.newTask(RunImpl.java:69)
	at com.vmlens.transformed.agent.bootstrap.parallelize.loop.ParallelizeLoop.newTask(ParallelizeLoop.java:47)
	at com.vmlens.transformed.agent.bootstrap.parallelize.facade.ParallelizeFacade.newTask(ParallelizeFacade.java:31)
	at com.vmlens.transformed.agent.bootstrap.callback.callbackaction.CallbackActionProcessorImpl.process(CallbackActionProcessorImpl.java:95)
	at com.vmlens.transformed.agent.bootstrap.callback.callbackaction.CallbackActionProcessorImpl.processWithCheckNewThread(CallbackActionProcessorImpl.java:72)
	at com.vmlens.transformed.agent.bootstrap.callback.MethodCallback.methodEnter(MethodCallback.java:37)
	at com.vmlens.test.maven.plugin.monitor.TestSynchronizedBlock$1.run(TestSynchronizedBlock.java)

      "main" #3 [5379] prio=5 os_prio=31 cpu=2197.03ms elapsed=30.75s tid=0x0000000132008800 nid=5379 in Object.wait()  [0x000000016bb84000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait0(java.base@24.0.1/Native Method)
	- waiting on <0x0000000782900000> (a com.vmlens.test.maven.plugin.monitor.TestSynchronizedBlock$1)
	at java.lang.Object.wait(java.base@24.0.1/Object.java:389)
	at java.lang.Thread.join(java.base@24.0.1/Thread.java:1860)
	- locked <0x0000000782900000> (a com.vmlens.test.maven.plugin.monitor.TestSynchronizedBlock$1)
	at java.lang.Thread.join(java.base@24.0.1/Thread.java:1936)
	at com.vmlens.test.maven.plugin.monitor.TestSynchronizedBlock.testUpdate(TestSynchronizedBlock.java:30)
	at java.lang.invoke.LambdaForm$DMH/0x00000fff011c0400.invokeVirtual(java.base@24.0.1/LambdaForm$DMH)
	at java.lang.invoke.LambdaForm$MH/0x00000fff011c0c00.invoke(java.base@24.0.1/LambdaForm$MH)
	at java.lang.invoke.Invokers$Holder.invokeExact_MT(java.base@24.0.1/Invokers$Holder)
	at jdk.internal.reflect.DirectMethodHandleAccessor.invokeImpl(java.base@24.0.1/DirectMethodHandleAccessor.java:154)
	at jdk.internal.reflect.DirectMethodHandleAccessor.invoke(java.base@24.0.1/DirectMethodHandleAccessor.java:104)
	at java.lang.reflect.Method.invoke(java.base@24.0.1/Method.java:565)
	*
	*
	*  main  thread is executing trying to join but this thread is not allowed to
	* run

         */
        if(startedThreadRunning) {
            if(runStateContext.isBlocked(sendEvent)) {
                return new RunStateAndResult<>(new RunStateActive(runStateContext),true);
            }
            return new RunStateAndResult<>(this,false);
        }


        return new RunStateAndResult<>(this,false);
    }


    @Override
    public RunState beforeLockExitWaitOrThreadStart(ExecuteBeforeEvent lockExitOrWaitEvent, ThreadLocalWhenInTest threadLocalDataWhenInTest, SendEvent sendEvent) {
        // Fixme probably not correct
        AfterContextForStateMachine afterContext = new
                AfterContextForStateMachine(threadLocalDataWhenInTest,lockExitOrWaitEvent);
        process(afterContext, sendEvent, runStateContext);
        return this;
    }

    @Override
    public RunState afterLockExitWaitOrThreadStart(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        // Fixme probably not correct
        return this;
    }

    @Override
    public ActualRun actualRun() {
        return runStateContext.actualRun();
    }

    @Override
    public boolean isEnded() {
        return false;
    }
}
