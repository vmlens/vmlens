package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;
import gnu.trove.map.hash.TLongObjectHashMap;

/**
 * the relation thread id to index is needed for thread start -> begin
 * and thread join
 *
 */
public class ThreadIdToState {
    // Package Visible for Test
    final TLongObjectHashMap<TestThreadState> threadIdToState = new TLongObjectHashMap<>();


    public void add(TestThreadState testThreadState) {
        threadIdToState.put(testThreadState.threadId(), testThreadState);
    }
    public int threadIndexForThreadId(long id)  {
        return threadIdToState.get(id).threadIndex();
    }
}
