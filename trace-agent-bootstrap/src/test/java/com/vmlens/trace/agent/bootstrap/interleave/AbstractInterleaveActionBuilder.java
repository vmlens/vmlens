package com.vmlens.trace.agent.bootstrap.interleave;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.*;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.MonitorKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.ReadWriteLockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.ReentrantLockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey.*;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.MethodIdByteCodePositionAndThreadIndexFactory.threadIndex;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.toArray;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public abstract class AbstractInterleaveActionBuilder {

    private final TLinkedList<TLinkableWrapper<InterleaveAction>> run = new TLinkedList<>();

    protected void lockEnter(int threadIndex, LockKey lockKey) {
        run.add(wrap(new LockEnterImpl(threadIndex(threadIndex),lockKey)));
    }

    protected void lockExit(int threadIndex, LockKey lockKey) {
        run.add(wrap(new LockExit(threadIndex(threadIndex),lockKey)));
    }

    protected void conditionWaitEnter(int threadIndex, LockKey lockKey) {
        run.add(wrap(new ConditionWaitEnter(threadIndex(threadIndex),lockKey)));
    }

    protected void conditionWaitExit(int threadIndex, LockKey lockKey) {
        run.add(wrap(new ConditionWaitExit(threadIndex(threadIndex),lockKey)));
    }

    protected void volatileAccess(int threadIndex, VolatileKey volatileKey, int operation) {
        run.add(wrap(new VolatileAccess(threadIndex(threadIndex),volatileKey,operation)));
    }

    protected void threadStart(int threadIndex, int startedThreadIndex) {
        run.add(wrap(new ThreadStart(threadIndex(threadIndex),startedThreadIndex)));
    }

    protected void threadJoin(int threadIndex, int joinedThreadIndex) {
        run.add(wrap(new ThreadJoin(threadIndex(threadIndex),joinedThreadIndex)));
    }

    public void guineaPig(int index, String value) {
        run.add(wrap(new InterleaveActionGuineaPig(index,value)));
    }

    public void noOp(int index) {
        run.add(wrap(new NoOpInterleaveAction(index)));
    }

    protected VolatileKey atomic(long objectHashCode) {
        return new AtomicNonBlockingKey(objectHashCode);
    }

    protected VolatileKey arrayAccess(int index, long objectHashCode) {
        return new VolatileArrayKey(index,objectHashCode);
    }

    protected VolatileKey volatileField(int fieldId, long objectHashCode) {
        return new VolatileFieldKey(fieldId,objectHashCode);
    }

    protected VolatileKey staticVolatile(int fieldId) {
        return new VolatileStaticFieldKey(fieldId);
    }

    protected LockKey monitor(long objectHashCode) {
        return new MonitorKey(objectHashCode);
    }

    protected LockKey lock(long objectHashCode) {
        return new ReentrantLockKey(objectHashCode);
    }

    protected LockKey readWriteLock(boolean isRead, long objectHashCode) {
        return new ReadWriteLockKey(objectHashCode,isRead);
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
