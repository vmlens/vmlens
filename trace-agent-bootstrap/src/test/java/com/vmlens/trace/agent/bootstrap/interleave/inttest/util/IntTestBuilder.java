package com.vmlens.trace.agent.bootstrap.interleave.inttest.util;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.*;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier.BarrierNotify;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier.BarrierWaitEnter;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.BarrierKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey.VolatileKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.MethodIdByteCodePositionAndThreadIndexFactory.threadIndex;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class IntTestBuilder {

    ThreadIndexToPosition threadIndexToPosition = new ThreadIndexToPosition();
    private final  TLinkedList<TLinkableWrapper<InterleaveAction>> actualRun = new TLinkedList<>();

    public IntTestOperation enterRead(LockKey lockKey, int threadIndex) {
        return enter(lockKey,threadIndex,true);
    }

    public IntTestOperation enterWrite(LockKey lockKey, int threadIndex) {
        return enter(lockKey,threadIndex,false);
    }

    private IntTestOperation enter(LockKey lockKey, int threadIndex, boolean isRead) {
        LockEnterImpl lockEnterImpl = new LockEnterImpl(threadIndex(threadIndex),
                lockKey,isRead);
        actualRun.add(wrap(lockEnterImpl));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    public IntTestOperation exit(LockKey lockKey, int threadIndex) {
        LockExit lockExit = new LockExit(threadIndex(threadIndex),lockKey);
        actualRun.add(wrap(lockExit));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    public IntTestOperation getLockState(LockKey lockKey, int threadIndex) {
        GetLockState lockExit = new GetLockState(threadIndex(threadIndex),lockKey);
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
        ThreadJoin threadJoin = new ThreadJoin(threadIndex(threadIndex),joinedThreadIndex);
        actualRun.add(wrap(threadJoin));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    public IntTestOperation barrierWait(BarrierKey key, int threadIndex) {
        BarrierWaitEnter barrierWait = new BarrierWaitEnter(threadIndex(threadIndex),key);
        actualRun.add(wrap(barrierWait));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    public IntTestOperation barrierNotify(BarrierKey key, int threadIndex) {
        BarrierNotify barrierNotify = new BarrierNotify(threadIndex(threadIndex),key);
        actualRun.add(wrap(barrierNotify));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    public IntTestOperation conditionWaitEnter(LockKey key, int threadIndex) {
        ConditionWaitEnter condition = new ConditionWaitEnter(threadIndex(threadIndex),key);
        actualRun.add(wrap(condition));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    public IntTestOperation conditionWaitExit(LockKey key, int threadIndex) {
        ConditionWaitExit condition = new ConditionWaitExit(threadIndex(threadIndex),key);
        actualRun.add(wrap(condition));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    private IntTestOperation volatileAccess(VolatileKey volatileAccessKey, int threadIndex, int operation) {
        VolatileAccess volatileAccess = new VolatileAccess(threadIndex(threadIndex),volatileAccessKey, operation);
        actualRun.add(wrap(volatileAccess));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    public TLinkedList<TLinkableWrapper<InterleaveAction>> build() {
        return actualRun;
    }
}
