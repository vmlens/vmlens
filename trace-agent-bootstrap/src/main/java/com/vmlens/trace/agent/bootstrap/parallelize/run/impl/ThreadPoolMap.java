package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.event.specialevents.ParallelizeActionMultiJoin;
import com.vmlens.trace.agent.bootstrap.parallelize.run.JoinAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadStartedByPoolContext;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.list.linked.TLongLinkedList;
import gnu.trove.map.hash.THashMap;

import java.util.Iterator;

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
        TLinkedList<TLinkableWrapper<Runnable>> tasks = poolToTaskList.get(pool);
        if (tasks == null) {
            System.err.println("Thread pool shutdown called but no tasks were ever submitted to this pool.");
            System.err.println("Your test may not be testing the concurrent behavior you expect.");
            return TLinkableWrapper.emptyList();
        }
        TLinkedList<TLinkableWrapper<Thread>> toBeJoinedList = new TLinkedList<>();
        for(TLinkableWrapper<Runnable> task : tasks) {
            toBeJoinedList.add(wrap(taskToThread.get(task.element())));
        }
        return toBeJoinedList;
    }

    private TLinkedList<TLinkableWrapper<Thread>> joinTask(Runnable task) {
        Thread thread = taskToThread.get(task);
        if (thread == null) {
            // Task was never registered
            return TLinkableWrapper.emptyList();
        }
        return TLinkableWrapper.singleton(thread);
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

    public void checkAllThreadsJoined() {
        boolean threadAlive = false;
        Iterator<TLinkedList<TLinkableWrapper<Runnable>>> poolIter = poolToTaskList.values().iterator();
        while(poolIter.hasNext()) {
            TLinkedList<TLinkableWrapper<Runnable>> tasks = poolIter.next();
            for(TLinkableWrapper<Runnable> task : tasks) {
                Thread thread = taskToThread.get(task.element());
                threadAlive |= thread.isAlive();
            }

        }
        if(threadAlive) {
           System.err.println("There are still threads from the thread pool running.");
           System.err.println("Please stop all threads after one thread interleaving using shutdown().");
        }

    }
}
