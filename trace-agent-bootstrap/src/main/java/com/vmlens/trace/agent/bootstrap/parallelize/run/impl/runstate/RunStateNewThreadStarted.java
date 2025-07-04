package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.LockExitOrWaitEvent;
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

    public RunStateNewThreadStarted(RunStateContext runStateContext,
                                    ThreadWrapper startedThread,
                                    int threadIndexForNewTestTask) {
        this.runStateContext = runStateContext;
        this.startedThread = startedThread;
        this.threadIndexForNewTestTask = threadIndexForNewTestTask;
    }

    @Override
    public boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,
                            SendEvent sendEvent) {
        return false;
    }

    @Override
    public ActualRun actualRun() {
        return runStateContext.actualRun();
    }

    @Override
    public RunState after(AfterContextForStateMachine afterContext, SendEvent sendEvent) {
        process(afterContext, sendEvent, runStateContext, threadIndexForNewTestTask);
        return this;
    }

    @Override
    public RunState newTestTaskStarted(ThreadWrapper newWrapper) {
       return this;
    }

    @Override
    public RunStateAndResult<ThreadLocalWhenInTest> processNewTestTask(NewTaskContext newTaskContext,
                       Run run,
                       SendEvent sendEvent) {
        if (!startedThread.equals(newTaskContext.newThread())) {
            return RunStateAndResult.of(this, null);
        }
        ThreadLocalWhenInTest threadLocalDataWhenInTest = runStateContext.createForStartedThread(
                run, newTaskContext.threadLocalForParallelize(), threadIndexForNewTestTask, sendEvent);
        newTaskContext.threadLocalForParallelize().setThreadLocalDataWhenInTest(threadLocalDataWhenInTest);

        return RunStateAndResult.of(new RunStateActive(runStateContext),threadLocalDataWhenInTest);
    }

    @Override
    public RunStateAndResult<Boolean> checkBlocked(SendEvent sendEvent) {
        /*
         * we need to wait that the new thread was really started,
         * checking for blocked leads to failing tests
         * Perhaps because the thread does yet exist when the checkBlocked gets called
         *
         */
        return new RunStateAndResult<>(this,false);
    }


    @Override
    public RunState waitCallOrBeforeLockExit(LockExitOrWaitEvent lockExitOrWaitEvent, ThreadLocalWhenInTest threadLocalDataWhenInTest, SendEvent sendEvent) {
        // Fixme probably not correct
        AfterContextForStateMachine afterContext = new
                AfterContextForStateMachine(threadLocalDataWhenInTest,lockExitOrWaitEvent);
        process(afterContext, sendEvent, runStateContext, threadIndexForNewTestTask);
        return this;
    }

    @Override
    public RunState afterLockExitOrWait(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        // Fixme probably not correct
        return this;
    }
}
