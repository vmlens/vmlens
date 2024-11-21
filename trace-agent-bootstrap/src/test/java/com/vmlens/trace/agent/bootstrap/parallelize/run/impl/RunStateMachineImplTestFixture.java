package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;

public class RunStateMachineImplTestFixture {

    private final Run run;
    private final RunStateMachine runStateMachine;
    private final ThreadLocalWhenInTest mainTestThread;
    private final ThreadLocalDataWhenInTestMap runContext;
    private final ActualRunMock actualRunMock;


    private RunStateMachineImplTestFixture(RunStateMachineTestFactory factory, ActualRunMock actualRunMock) {
        this.actualRunMock = actualRunMock;
        ThreadLocalDataWhenInTestMap runContext = new ThreadLocalDataWhenInTestMap();
        RunStateMachine runStateMachine = factory.create(runContext);
        Run run = new RunTestAdapter(runStateMachine);
        ThreadLocalWhenInTest mainTestThread = runContext.createForMainTestThread(run, 1L);

        this.run = run;
        this.runStateMachine = runStateMachine;
        this.mainTestThread = mainTestThread;
        this.runContext = runContext;
    }

    public static RunStateMachineImplTestFixture createRunning(final CalculatedRun calculatedRun) {
        final ActualRunMock actualRunMock = new ActualRunMock(new ActualRunMockStrategyTake());

        return new RunStateMachineImplTestFixture(new RunStateMachineTestFactory() {
            @Override
            public RunStateMachine create(ThreadLocalDataWhenInTestMap threadLocalDataWhenInTestMap) {
                return new RunStateMachineFactoryImpl()
                        .createRunning(threadLocalDataWhenInTestMap, calculatedRun,
                                actualRunMock);
            }
        }, actualRunMock);
    }

    public static RunStateMachineImplTestFixture createInitial() {
        final ActualRunMock actualRunMock = new ActualRunMock(new ActualRunMockStrategyTake());

        return new RunStateMachineImplTestFixture(new RunStateMachineTestFactory() {
            @Override
            public RunStateMachine create(ThreadLocalDataWhenInTestMap threadLocalDataWhenInTestMap) {
                return new RunStateMachineFactoryImpl()
                        .createInitial(threadLocalDataWhenInTestMap, actualRunMock);
            }
        }, actualRunMock);
    }


    public RunStateMachine runStateMachine() {
        return runStateMachine;
    }

    public ThreadLocalWhenInTest mainTestThread() {
        return mainTestThread;
    }


    public Run run() {
        return run;
    }

    public ThreadLocalDataWhenInTestMap runContext() {
        return runContext;
    }

    public ActualRunMock actualRunMock() {
        return actualRunMock;
    }

    private interface RunStateMachineTestFactory {
        RunStateMachine create(ThreadLocalDataWhenInTestMap threadLocalDataWhenInTestMap);
    }
}
