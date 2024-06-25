package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRunObserverNoOp;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateMachineFactoryImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadLocalDataWhenInTestMap;
import com.vmlens.trace.agent.bootstrap.testfixture.QueueInMock;

import java.util.LinkedList;
import java.util.List;

public class RunStateMachineImplTestFixture {

    private final QueueIn queueIn;
    private final Run run;
    private final RunStateMachine runStateMachine;
    private final ThreadLocalDataWhenInTest mainTestThread;
    private final List<SerializableEvent> eventList;

    private RunStateMachineImplTestFixture(QueueIn queueIn, Run run, RunStateMachine runStateMachine,
                                           ThreadLocalDataWhenInTest mainTestThread,
                                           List<SerializableEvent> eventList) {
        this.queueIn = queueIn;
        this.run = run;
        this.runStateMachine = runStateMachine;
        this.mainTestThread = mainTestThread;
        this.eventList = eventList;
    }

    public static RunStateMachineImplTestFixture createRunning(final CalculatedRun calculatedRun) {
        return create(new RunStateMachineTestFactory() {
            @Override
            public RunStateMachine create(ThreadLocalDataWhenInTestMap threadLocalDataWhenInTestMap) {
                return new RunStateMachineFactoryImpl()
                        .createRunning(threadLocalDataWhenInTestMap, calculatedRun,
                                new ActualRun(new ActualRunObserverNoOp()));
            }
        });
    }

    public static RunStateMachineImplTestFixture createInitial() {
        return create(new RunStateMachineTestFactory() {
            @Override
            public RunStateMachine create(ThreadLocalDataWhenInTestMap threadLocalDataWhenInTestMap) {
                return new RunStateMachineFactoryImpl()
                        .createInitial(threadLocalDataWhenInTestMap, new ActualRun(new ActualRunObserverNoOp()));
            }
        });
    }

    private static RunStateMachineImplTestFixture create(RunStateMachineTestFactory factory) {
        ThreadLocalDataWhenInTestMap runContext = new ThreadLocalDataWhenInTestMap(1, 1);
        RunStateMachine runStateMachine = factory.create(runContext);
        List<SerializableEvent> eventList = new LinkedList<>();
        Run run = new RunTestAdapter(runStateMachine);
        QueueIn queueIn = new QueueInMock(eventList);
        ThreadLocalDataWhenInTest mainTestThread = runContext.create(run, queueIn, 1L);

        return new RunStateMachineImplTestFixture(queueIn, run, runStateMachine, mainTestThread, eventList);
    }

    public RunStateMachine runStateMachine() {
        return runStateMachine;
    }

    public ThreadLocalDataWhenInTest mainTestThread() {
        return mainTestThread;
    }

    public List<SerializableEvent> eventList() {
        return eventList;
    }

    public QueueIn queueIn() {
        return queueIn;
    }

    public Run run() {
        return run;
    }

    private interface RunStateMachineTestFactory {
        RunStateMachine create(ThreadLocalDataWhenInTestMap threadLocalDataWhenInTestMap);
    }
}
