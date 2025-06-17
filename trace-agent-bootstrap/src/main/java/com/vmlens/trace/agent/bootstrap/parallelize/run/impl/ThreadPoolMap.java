package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.run.JoinAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadStartedByPoolContext;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.map.hash.THashMap;

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
        list.add(TLinkableWrapper.wrap(threadStartedByPoolContext.task()));
    }

    public void process(JoinAction threadJoinedAction) {

        if(threadJoinedAction.taskOrPool() instanceof Runnable) {
            joinTask(threadJoinedAction.queueIn(),(Runnable) threadJoinedAction.taskOrPool());
        } else {
            joinAll(threadJoinedAction.queueIn(),threadJoinedAction.taskOrPool());
        }
    }

    private void joinAll(QueueIn queueIn, Object pool) {

    }

    private void joinTask(QueueIn queueIn, Runnable task) {

    }

    // thread ids for join




}
