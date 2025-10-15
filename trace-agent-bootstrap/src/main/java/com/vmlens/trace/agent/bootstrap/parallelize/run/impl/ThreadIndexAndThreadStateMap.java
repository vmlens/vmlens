package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;


import com.vmlens.trace.agent.bootstrap.callback.threadlocal.FirstMethodInThread;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.description.ThreadDescription;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.event.warning.InfoMessageEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import gnu.trove.iterator.TIntObjectIterator;
import gnu.trove.list.linked.TIntLinkedList;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.map.hash.TLongIntHashMap;


public class ThreadIndexAndThreadStateMap implements CreateInterleaveActionContext {
    private int maxThreadIndex;
    // Package Visible for Test
    final TLongIntHashMap threadIdToIndex = new TLongIntHashMap();
    private final TIntObjectHashMap<ThreadForParallelize> threadIndexToThreadState = new TIntObjectHashMap<>();

    public ThreadLocalWhenInTest createForMainTestThread(Run run,
                                                         ThreadLocalForParallelize threadLocalForParallelize,
                                                         QueueIn queueIn) {
        ThreadLocalWhenInTest threadLocalDataWhenInTest = new ThreadLocalWhenInTest(run, maxThreadIndex,null);
        threadIdToIndex.put(threadLocalForParallelize.threadId(),maxThreadIndex);
        threadIndexToThreadState.put(maxThreadIndex,threadLocalForParallelize.threadForParallelize());

        ThreadDescription threadDescription = new
                ThreadDescription(run.loopId(), run.runId(), maxThreadIndex, threadLocalForParallelize.threadId(),
                threadLocalForParallelize.threadName());
        queueIn.offer(threadDescription);

        maxThreadIndex++;
        return threadLocalDataWhenInTest;
    }

    public ThreadLocalWhenInTest createForStartedThread(Run run, ThreadLocalForParallelize threadLocalForParallelize,
                                                        int newThreadIndex,
                                                        SendEvent sendEvent,
                                                        FirstMethodInThread firstMethodInThread) {
        ThreadLocalWhenInTest threadLocalDataWhenInTest = new ThreadLocalWhenInTest(run, newThreadIndex,firstMethodInThread);
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

    public ThreadState isBlocked(int index) {
        if(threadIndexToThreadState.get(index) == null) {
            return ThreadState.TERMINATED;
        }
        return threadIndexToThreadState.get(index).isBlocked();
    }

    public TIntLinkedList getActiveThreadIndices() {
        TIntLinkedList active = new TIntLinkedList();
        TIntObjectIterator<ThreadForParallelize> iter = threadIndexToThreadState.iterator();
        while(iter.hasNext()) {
            iter.advance();
            if(isActive(iter.value())) {
                active.add(iter.key());
            }
        }
        return active;
    }

    private boolean isActive(ThreadForParallelize threadForParallelize) {
        ThreadState state = threadForParallelize.isBlocked();
        switch (state) {
            case ACTIVE: {
                return true;
            }
        }
        return false;
    }

    public void logStackTrace(SendEvent sendEvent, int blockedThreadIndex) {
        sendEvent.sendSerializable(new InfoMessageEvent(new String[]{ "RunId:" + sendEvent.runId() }));
        sendEvent.sendSerializable(new InfoMessageEvent(new String[]{ "BlockedIndex:" + blockedThreadIndex }));

        TIntObjectIterator<ThreadForParallelize> iterator = threadIndexToThreadState.iterator();
        while(iterator.hasNext()) {
            iterator.advance();
            int index = iterator.key();
            ThreadForParallelize threadForParallelize = iterator.value();
            StackTraceElement[]  myStacktrace = threadForParallelize.getStackTrace();
            String[] message = new String[myStacktrace.length + 1];
            message[0] = "ThreadIndex:" + index;
            int i = 1;
            for(StackTraceElement element :  myStacktrace ) {
                message[i] = element.getClassName() + "." + element.getMethodName();
                i++;
            }
            sendEvent.sendSerializable(new InfoMessageEvent(message));
        }

    }
}
