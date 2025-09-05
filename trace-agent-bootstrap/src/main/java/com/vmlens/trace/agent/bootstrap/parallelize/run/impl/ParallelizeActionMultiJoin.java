package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.ParallelizeActionAfter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadJoinedEvent;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.MultiJoin;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveInfo;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;
import gnu.trove.iterator.TIntIterator;
import gnu.trove.iterator.TLongIterator;
import gnu.trove.list.linked.TIntLinkedList;
import gnu.trove.list.linked.TLongLinkedList;

import static com.vmlens.trace.agent.bootstrap.event.EventTypeThread.THREAD_POOL;

public class ParallelizeActionMultiJoin implements ParallelizeActionAfter {

    private final TLongLinkedList joinedThreadIds;
    private final int loopId;
    private final int runId;
    private final int inMethodId;
    private final int position;

    public ParallelizeActionMultiJoin(Run run, TLongLinkedList joinedThreadIds, int inMethodId, int position) {
        this.joinedThreadIds = joinedThreadIds;
        this.runId = run.runId();
        this.loopId = run.loopId();
        this.inMethodId = inMethodId;
        this.position = position;
    }


    @Override
    public void after(InterleaveRun interleaveRun,
                      CreateInterleaveActionContext context,
                      ThreadLocalWhenInTestForParallelize threadLocalWhenInTestForParallelize,
                      SendEvent sendEvent) {
        TIntLinkedList threadIndices = new TIntLinkedList();
        TLongIterator iter = joinedThreadIds.iterator();
        while(iter.hasNext()) {
            long id = iter.next();
            threadIndices.add(context.threadIndexForThreadId(id));
        }

        InterleaveInfo info = interleaveRun.after(new MultiJoin(threadLocalWhenInTestForParallelize.threadIndex(), threadIndices));

        TIntIterator indexIter = threadIndices.iterator();
        while(indexIter.hasNext()) {
            int index = indexIter.next();
            ThreadJoinedEvent threadJoinedEvent = new ThreadJoinedEvent();
            threadJoinedEvent.setEventType(THREAD_POOL.code());
            threadJoinedEvent.setThreadIndex(threadLocalWhenInTestForParallelize.threadIndex());
            threadJoinedEvent.setLoopId(loopId);
            threadJoinedEvent.setRunId(runId);
            threadJoinedEvent.setRunPosition(info.runPosition());
            threadJoinedEvent.setMethodCounter(threadLocalWhenInTestForParallelize);
            threadJoinedEvent.setBytecodePosition(position);
            threadJoinedEvent.setMethodId(inMethodId);
            threadJoinedEvent.setJoinedThreadIndex(index);
            sendEvent.sendSerializable(threadJoinedEvent);
        }

    }
}
