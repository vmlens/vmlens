package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.anarsoft.trace.agent.description.ThreadDescription;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.iterator.TIntObjectIterator;
import gnu.trove.list.linked.TIntLinkedList;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.map.hash.TLongIntHashMap;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;


public class ThreadIndexAndThreadStateMap implements CreateInterleaveActionContext {
    private int maxThreadIndex;
    // Package Visible for Test
    final TLongIntHashMap threadIdToIndex = new TLongIntHashMap();
    private final TIntObjectHashMap<ThreadForParallelize> threadIndexToThreadState = new TIntObjectHashMap<>();

    public ThreadLocalWhenInTest createForMainTestThread(Run run, ThreadLocalForParallelize threadLocalForParallelize,
                                                         TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents) {
        ThreadLocalWhenInTest threadLocalDataWhenInTest = new ThreadLocalWhenInTest(run, maxThreadIndex);
        threadIdToIndex.put(threadLocalForParallelize.threadId(),maxThreadIndex);
        threadIndexToThreadState.put(maxThreadIndex,threadLocalForParallelize.threadForParallelize());

        ThreadDescription threadDescription = new
                ThreadDescription(run.loopId(), run.runId(), maxThreadIndex, threadLocalForParallelize.threadId(),
                threadLocalForParallelize.threadName());
        serializableEvents.add(wrap(threadDescription));

        maxThreadIndex++;
        return threadLocalDataWhenInTest;
    }

    public ThreadLocalWhenInTest createForStartedThread(Run run, ThreadLocalForParallelize threadLocalForParallelize,
                                                        int newThreadIndex,
                                                        SendEvent sendEvent) {
        ThreadLocalWhenInTest threadLocalDataWhenInTest = new ThreadLocalWhenInTest(run, newThreadIndex);
        threadIdToIndex.put(threadLocalForParallelize.threadId(), newThreadIndex);
        threadIndexToThreadState.put(newThreadIndex,threadLocalForParallelize.threadForParallelize());

        ThreadDescription threadDescription = new
                ThreadDescription(run.loopId(), run.runId(), newThreadIndex, threadLocalForParallelize.threadId(),
                threadLocalForParallelize.threadName());
        sendEvent.sendSerializable(threadDescription);

        return threadLocalDataWhenInTest;
    }

    public int getThreadIndexForNewTestThread() {
        int temp = maxThreadIndex;
        maxThreadIndex++;
        return temp;
    }

    @Override
    public int threadIndexForThreadId(long threadId) {
        return threadIdToIndex.get(threadId);
    }

    public ThreadState isBlocked(int index,SendEvent sendEvent) {
        if(threadIndexToThreadState.get(index) == null) {
            return ThreadState.TERMINATED;
        }
        return threadIndexToThreadState.get(index).isBlocked(sendEvent);
    }

    public TIntLinkedList getActiveThreadIndices(SendEvent sendEvent) {
        TIntLinkedList active = new TIntLinkedList();
        TIntObjectIterator<ThreadForParallelize> iter = threadIndexToThreadState.iterator();
        while(iter.hasNext()) {
            iter.advance();
            if(isActive(iter.value(),sendEvent)) {
                active.add(iter.key());
            }
        }
        return active;
    }

    private boolean isActive(ThreadForParallelize threadForParallelize,SendEvent sendEvent) {
        ThreadState state = threadForParallelize.isBlocked(sendEvent);
        switch (state) {
            case ACTIVE: {
                return true;
            }
        }
        return false;
    }

}
