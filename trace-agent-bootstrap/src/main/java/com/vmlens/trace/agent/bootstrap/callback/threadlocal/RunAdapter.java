package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.AfterContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunForCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.emptyList;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class RunAdapter {

    private final RunForCallback run;

    public RunAdapter(RunForCallback run) {
        this.run = run;
    }

    public void after(RuntimeEvent runtimeEventIn,
                      ThreadLocalWhenInTest threadLocalWhenInTest,
                      QueueIn queueIn) {
        runtimeEventIn.setThreadIndex(threadLocalWhenInTest.threadIndex());
        run.after(new AfterContext(threadLocalWhenInTest, runtimeEventIn, queueIn));
    }

    public void newTestTaskStarted(RunnableOrThreadWrapper newWrapper) {
        run.newTestTaskStarted(newWrapper);
    }

}
