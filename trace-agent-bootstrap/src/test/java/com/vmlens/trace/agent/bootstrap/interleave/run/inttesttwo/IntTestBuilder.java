package com.vmlens.trace.agent.bootstrap.interleave.run.inttesttwo;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.LockEnterImpl;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.LockExit;
import com.vmlens.trace.agent.bootstrap.interleave.lock.Monitor;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class IntTestBuilder {

    ThreadIndexToPosition threadIndexToPosition = new ThreadIndexToPosition();
    private final  TLinkedList<TLinkableWrapper<InterleaveAction>> actualRun = new TLinkedList<>();

    public IntTestOperation enter(long objectHashCode, int threadIndex) {
        LockEnterImpl lockEnterImpl = new LockEnterImpl(threadIndex,new Monitor(objectHashCode));
        actualRun.add(wrap(lockEnterImpl));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));

    }

    public IntTestOperation exit(long objectHashCode, int threadIndex) {
        LockExit lockExit = new LockExit(threadIndex,new Monitor(objectHashCode));
        actualRun.add(wrap(lockExit));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));

    }

    public TLinkedList<TLinkableWrapper<InterleaveAction>> build() {
        return actualRun;
    }



}
