package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.run.JoinAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadStartedByPoolContext;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.list.linked.TLongLinkedList;
import gnu.trove.map.hash.THashMap;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class ThreadPoolMap {

    // Task to thread
    private final THashMap<Runnable,Thread> taskToThread = new THashMap<>();

    // and Pool to task list
    private final THashMap<Object, TLinkedList<TLinkableWrapper<Runnable>>> poolToTaskList = new THashMap<>();

    public void add(ThreadStartedByPoolContext threadStartedByPoolContext) {
        taskToThread.put(threadStartedByPoolContext.task(),threadStartedByPoolContext.startedThread());
        TLinkedList<TLinkableWrapper<Runnable>> list = poolToTaskList.get(threadStartedByPoolContext.pool());
        if(list == null) {
            list = new TLinkedList<>();
            poolToTaskList.put(threadStartedByPoolContext.pool(),list);
        }
        list.add(wrap(threadStartedByPoolContext.task()));
    }

    public TLinkedList<TLinkableWrapper<Thread>> process(JoinAction threadJoinedAction) {

        if(threadJoinedAction.taskOrPool() instanceof Runnable) {
           return joinTask((Runnable) threadJoinedAction.taskOrPool());
        } else {
            return joinAll(threadJoinedAction.taskOrPool());
        }
    }

    private TLinkedList<TLinkableWrapper<Thread>> joinAll(Object pool) {
        TLinkedList<TLinkableWrapper<Thread>> toBeJoinedList = new TLinkedList<>();
        TLinkedList<TLinkableWrapper<Runnable>> tasks = poolToTaskList.get(pool);
        for(TLinkableWrapper<Runnable> task : tasks) {
            toBeJoinedList.add(wrap(taskToThread.get(task.element())));
        }
        return toBeJoinedList;
    }

    private TLinkedList<TLinkableWrapper<Thread>> joinTask(Runnable task) {
        return TLinkableWrapper.singleton(taskToThread.get(task));

    }

    private ParallelizeActionMultiJoin join(Run run, TLinkedList<TLinkableWrapper<Thread>> toBeJoinedList, JoinAction threadJoinedAction) {
        TLongLinkedList joinedThreadIds = new TLongLinkedList();
        for(TLinkableWrapper<Thread> toBeJoined : toBeJoinedList) {
            if(toBeJoined.element().isAlive()) {
                try {
                    toBeJoined.element().join();
                    joinedThreadIds.add(toBeJoined.element().getId());

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return new ParallelizeActionMultiJoin(run,joinedThreadIds,threadJoinedAction.inMethodId(),threadJoinedAction.position());

    }

}
