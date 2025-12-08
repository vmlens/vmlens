package com.vmlens.trace.agent.bootstrap.interleave;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.*;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.MonitorKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.ReadWriteLockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.ReentrantLockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey.*;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestOperation;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.ThreadIndexToPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.MethodIdByteCodePositionAndThreadIndexFactory.threadIndex;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.toArray;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public abstract class AbstractInterleaveActionBuilder {

    private final ThreadIndexToPosition threadIndexToPosition = new ThreadIndexToPosition();
    private final TLinkedList<TLinkableWrapper<InterleaveAction>> run = new TLinkedList<>();

    protected IntTestOperation lockEnter(int threadIndex, LockKey lockKey) {
        run.add(wrap(new LockEnterImpl(threadIndex(threadIndex),lockKey,false)));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    protected IntTestOperation lockExit(int threadIndex, LockKey lockKey) {
        run.add(wrap(new LockExit(threadIndex(threadIndex),lockKey)));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    protected IntTestOperation conditionWaitEnter(int threadIndex, LockKey lockKey) {
        run.add(wrap(new ConditionWaitEnter(threadIndex(threadIndex),lockKey)));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    protected IntTestOperation conditionWaitExit(int threadIndex, LockKey lockKey) {
        run.add(wrap(new ConditionWaitExit(threadIndex(threadIndex),lockKey)));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    protected IntTestOperation  volatileAccess(int threadIndex, VolatileKey volatileKey, int operation) {
        run.add(wrap(new VolatileAccess(threadIndex(threadIndex),volatileKey,operation)));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    protected IntTestOperation  threadStart(int threadIndex, int startedThreadIndex) {
        run.add(wrap(new ThreadStart(threadIndex(threadIndex),startedThreadIndex)));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    protected IntTestOperation  threadJoin(int threadIndex, int joinedThreadIndex) {
        run.add(wrap(new ThreadJoin(threadIndex(threadIndex),joinedThreadIndex)));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    protected IntTestOperation  guineaPig(int threadIndex, String value) {
        run.add(wrap(new InterleaveActionGuineaPig(threadIndex,value)));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    protected IntTestOperation  noOp(int threadIndex) {
        run.add(wrap(new NoOpInterleaveAction(threadIndex)));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }

    protected IntTestOperation lastActionInThread(int threadIndex) {
        run.add(wrap(new LastInterleaveActionInThread(threadIndex)));
        return new IntTestOperation(threadIndexToPosition.next(threadIndex));
    }


    // Lock Keys
    protected LockKey monitor(long objectHashCode) {
        return new MonitorKey(objectHashCode);
    }

    protected LockKey lock(long objectHashCode) {
        return new ReentrantLockKey(objectHashCode);
    }

    // Volatile Keys
    protected VolatileKey atomic(long objectHashCode) {
        return new AtomicNonBlockingKey(objectHashCode);
    }

    protected VolatileKey staticVolatile(int fieldId) {
        return new VolatileStaticFieldKey(fieldId);
    }

    protected VolatileKey arrayAccess(int index, long objectHashCode) {
        return new VolatileArrayKey(index,objectHashCode);
    }

    protected VolatileKey volatileField(int fieldId, long objectHashCode) {
        return new VolatileFieldKey(fieldId,objectHashCode);
    }


    protected LockKey readWriteLock(long objectHashCode) {
        return new ReadWriteLockKey(objectHashCode);
    }

    protected abstract void addActions();

    public  TLinkedList<TLinkableWrapper<InterleaveAction>> build() {
        addActions();
        return run;
    }

    public InterleaveAction[]  buildArray() {
        TLinkedList<TLinkableWrapper<InterleaveAction>> list = build();
        return toArray(InterleaveAction.class, list);
    }

}
