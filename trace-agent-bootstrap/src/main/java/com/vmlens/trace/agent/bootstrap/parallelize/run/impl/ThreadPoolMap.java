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

    public ParallelizeActionMultiJoin process(Run run, JoinAction threadJoinedAction) {

        if(threadJoinedAction.taskOrPool() instanceof Runnable) {
           return joinTask(run,(Runnable) threadJoinedAction.taskOrPool(),threadJoinedAction);
        } else {
            return joinAll(run,threadJoinedAction.taskOrPool(),threadJoinedAction);
        }
    }

    private ParallelizeActionMultiJoin joinAll(Run run, Object pool,JoinAction threadJoinedAction) {
        TLinkedList<TLinkableWrapper<Thread>> toBeJoinedList = new TLinkedList<>();
        TLinkedList<TLinkableWrapper<Runnable>> tasks = poolToTaskList.get(pool);
        for(TLinkableWrapper<Runnable> task : tasks) {
            toBeJoinedList.add(wrap(taskToThread.get(task.element())));
        }
        return join(run,toBeJoinedList,threadJoinedAction);
    }

    private ParallelizeActionMultiJoin joinTask(Run run, Runnable task, JoinAction threadJoinedAction) {
        TLinkedList<TLinkableWrapper<Thread>> toBeJoinedList = TLinkableWrapper.singleton(taskToThread.get(task));
        return join(run,toBeJoinedList,threadJoinedAction);

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
