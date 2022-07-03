package com.vmlens.trace.agent.bootstrap.parallelize.runState;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.ThreadIndexContainer;
import com.vmlens.trace.agent.bootstrap.interleave.facade.InterleaveFacade;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.SyncAction;
import gnu.trove.iterator.TLongObjectIterator;
import gnu.trove.map.hash.TLongObjectHashMap;

public class ThreadIdToState implements ThreadIndexContainer {

    private final TLongObjectHashMap<ThreadState> map = new TLongObjectHashMap<ThreadState>();

    // ToDo
    public boolean hasMoreThanOneThread() {
        return true;
    }

    public void afterSyncAction(InterleaveFacade interleaveFacade, SyncAction syncAction, long threadId) {
        ThreadState state = map.get(threadId);
        Position position = new Position(state.getIndex(), state.getAndIncrementPosition());
        interleaveFacade.afterSyncAction(position, hasMoreThanOneThread(), syncAction);
    }

    int threadIndex(long threadId) {
        return map.get(threadId).getIndex();
    }

    @Override
    public boolean exists(int threadIndex) {
        TLongObjectIterator<ThreadState> iterator = map.iterator();
        while (iterator.hasNext()) {
            iterator.advance();
            if (iterator.value().getIndex() == threadIndex) {
                return true;
            }
        }
        return false;
    }

    // For tests
    public void begin(Integer threadIndex, long threadId) {
        map.put(threadId, new ThreadState(threadIndex));
    }

    public void end(Long threadId) {
        map.remove(threadId);
    }
}
