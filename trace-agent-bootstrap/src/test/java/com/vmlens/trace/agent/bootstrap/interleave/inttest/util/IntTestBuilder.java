package com.vmlens.trace.agent.bootstrap.interleave.inttest.util;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.*;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier.BarrierNotify;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier.BarrierWaitEnter;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey.BarrierKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.volatileaccesskey.VolatileKey;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class IntTestBuilder {

    ThreadIndexToPosition threadIndexToPosition = new ThreadIndexToPosition();
    private final  TLinkedList<TLinkableWrapper<InterleaveAction>> actualRun = new TLinkedList<>();

    public IntTestOperation enter(LockKey lockKey, int threadIndex) {
        LockEnterImpl lockEnterImpl = new LockEnterImpl(threadIndex,lockKey);
        actualRun.add(wrap(lockEnterImpl));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    public IntTestOperation exit(LockKey lockKey, int threadIndex) {
        LockExit lockExit = new LockExit(threadIndex,lockKey);
        actualRun.add(wrap(lockExit));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    public IntTestOperation read(VolatileKey volatileAccessKey, int threadIndex) {
        return volatileAccess(volatileAccessKey,threadIndex,MemoryAccessType.IS_READ);
    }

    public IntTestOperation write(VolatileKey volatileAccessKey, int threadIndex) {
        return volatileAccess(volatileAccessKey,threadIndex,MemoryAccessType.IS_WRITE);
    }

    public IntTestOperation join(int joinedThreadIndex, int threadIndex) {
        ThreadJoin threadJoin = new ThreadJoin(threadIndex,joinedThreadIndex);
        actualRun.add(wrap(threadJoin));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    public IntTestOperation barrierWait(BarrierKey key, int threadIndex) {
        BarrierWaitEnter barrierWait = new BarrierWaitEnter(threadIndex,key);
        actualRun.add(wrap(barrierWait));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    public IntTestOperation barrierNotify(BarrierKey key, int threadIndex) {
        BarrierNotify barrierNotify = new BarrierNotify(threadIndex,key);
        actualRun.add(wrap(barrierNotify));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    public IntTestOperation conditionWaitEnter(LockKey key, int threadIndex) {
        ConditionWaitEnter condition = new ConditionWaitEnter(threadIndex,key);
        actualRun.add(wrap(condition));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    public IntTestOperation conditionWaitExit(LockKey key, int threadIndex) {
        ConditionWaitExit condition = new ConditionWaitExit(threadIndex,key);
        actualRun.add(wrap(condition));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    private IntTestOperation volatileAccess(VolatileKey volatileAccessKey, int threadIndex, int operation) {
        VolatileAccess volatileAccess = new VolatileAccess(threadIndex,volatileAccessKey, operation);
        actualRun.add(wrap(volatileAccess));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    public TLinkedList<TLinkableWrapper<InterleaveAction>> build() {
        return actualRun;
    }
}
