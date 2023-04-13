package com.vmlens.trace.agent.bootstrap.interleave.calculatedRun;

import com.vmlens.trace.agent.bootstrap.interleave.block.InterleaveActionWithPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class CalculatedRunInitial implements CalculatedRun {

    private final TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun =
            new TLinkedList<>();
    private  int currentActiveThreadId = -1;

    @Override
    public boolean isActive(int threadId) {
        if(currentActiveThreadId == -1) {
            currentActiveThreadId = threadId;
            return true;
        }
      return threadId == currentActiveThreadId;
    }

    @Override
    public void after(InterleaveActionWithPositionFactory interleaveActionWithPosition) {
        actualRun.add(new TLinkableWrapper(interleaveActionWithPosition));
        currentActiveThreadId = -1;
    }

    @Override
    public TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun() {
        return actualRun;
    }


}
