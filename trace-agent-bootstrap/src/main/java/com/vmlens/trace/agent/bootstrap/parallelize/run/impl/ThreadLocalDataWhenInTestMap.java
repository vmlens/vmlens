package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.anarsoft.trace.agent.description.ThreadDescription;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.map.hash.TLongObjectHashMap;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;


public class ThreadLocalDataWhenInTestMap implements CreateInterleaveActionContext {
    private int maxThreadIndex;
    // Package Visible for Test
    final TLongObjectHashMap<ThreadLocalWhenInTest> threadIdToState = new TLongObjectHashMap<>();

    public ThreadLocalWhenInTest createForMainTestThread(Run run, ThreadLocalForParallelize threadLocalForParallelize,
                                                         TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents) {
        ThreadLocalWhenInTest threadLocalDataWhenInTest = new ThreadLocalWhenInTest(run, maxThreadIndex);
        threadIdToState.put(threadLocalForParallelize.threadId(), threadLocalDataWhenInTest);

        ThreadDescription threadDescription = new
                ThreadDescription(run.loopId(), run.runId(), maxThreadIndex, threadLocalForParallelize.threadId(),
                threadLocalForParallelize.threadName());
        serializableEvents.add(wrap(threadDescription));

        maxThreadIndex++;
        return threadLocalDataWhenInTest;
    }

    public ThreadLocalWhenInTest createForStartedThread(Run run, ThreadLocalForParallelize threadLocalForParallelize,
                                                        int newThreadIndex,
                                                        TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents) {
        ThreadLocalWhenInTest threadLocalDataWhenInTest = new ThreadLocalWhenInTest(run, newThreadIndex);
        threadIdToState.put(threadLocalForParallelize.threadId(), threadLocalDataWhenInTest);

        ThreadDescription threadDescription = new
                ThreadDescription(run.loopId(), run.runId(), newThreadIndex, threadLocalForParallelize.threadId(),
                threadLocalForParallelize.threadName());
        serializableEvents.add(wrap(threadDescription));

        return threadLocalDataWhenInTest;
    }

    public int getThreadIndexForNewTestThread() {
        int temp = maxThreadIndex;
        maxThreadIndex++;
        return temp;
    }

    @Override
    public int threadIndexForThreadId(long threadId) {
        return threadIdToState.get(threadId).threadIndex();
    }
}
