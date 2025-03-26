package com.vmlens.trace.agent.bootstrap.interleave.inttest.util;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.LockEnterImpl;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.LockExit;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadJoin;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.VolatileAccess;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.volatileaccesskey.VolatileKey;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.lock.Lock;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class IntTestBuilder {

    ThreadIndexToPosition threadIndexToPosition = new ThreadIndexToPosition();
    private final  TLinkedList<TLinkableWrapper<InterleaveAction>> actualRun = new TLinkedList<>();

    public IntTestOperation enter(LockKey lockKey, int threadIndex) {
        LockEnterImpl lockEnterImpl = new LockEnterImpl(threadIndex,new Lock(lockKey));
        actualRun.add(wrap(lockEnterImpl));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    public IntTestOperation exit(LockKey lockKey, int threadIndex) {
        LockExit lockExit = new LockExit(threadIndex,new Lock(lockKey));
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


    private IntTestOperation volatileAccess(VolatileKey volatileAccessKey, int threadIndex, int operation) {
        VolatileAccess volatileAccess = new VolatileAccess(threadIndex,volatileAccessKey, operation);
        actualRun.add(wrap(volatileAccess));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    public TLinkedList<TLinkableWrapper<InterleaveAction>> build() {
        return actualRun;
    }
}
