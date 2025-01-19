package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstates;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWhenInTestAndSerializableEvents;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWhenInTestForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ProcessRuntimeEventCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateAndResult;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWhenInTestAndSerializableEvents.empty;

public class RunStateAtomicOperation extends RunStateAtomicOperationAbstract {

    private final RunStateActive nextState;

    public RunStateAtomicOperation(int threadIndexOfAtomicOperation, RunStateActive nextState) {
        super(threadIndexOfAtomicOperation);
        this.nextState = nextState;
    }

    @Override
    public RunStateAndResult<ThreadLocalWhenInTestAndSerializableEvents> processNewTestTask(RunnableOrThreadWrapper newWrapper,
                                                                                            ThreadLocalForParallelize threadLocalForParallelize,
                                                                                            Run run) {
        return RunStateAndResult.of(this, empty());
    }

    @Override
    public RunStateAndResult<RuntimeEvent> endAtomicOperation(ProcessRuntimeEventCallback processRuntimeEventCallback,
                                                              RuntimeEvent runtimeEvent,
                                                              ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        return RunStateAndResult.of(nextState,
                processRuntimeEventCallback.callAfterFromState(runtimeEvent, threadLocalDataWhenInTest));
    }
}
