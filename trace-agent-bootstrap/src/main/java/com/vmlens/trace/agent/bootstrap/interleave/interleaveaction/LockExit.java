package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.LockStartOperation;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.lockcontainer.Block;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.lockcontainer.BlockEnd;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;

import java.util.Objects;

public class LockExit implements InterleaveAction  {

    private final MethodIdByteCodePositionAndThreadIndex methodIdByteCodePositionAndThreadIndex;
    private final LockKey lockOrMonitor;

    public LockExit(MethodIdByteCodePositionAndThreadIndex methodIdByteCodePositionAndThreadIndex,
                    LockKey lockOrMonitor) {
        this.methodIdByteCodePositionAndThreadIndex = methodIdByteCodePositionAndThreadIndex;
        this.lockOrMonitor = lockOrMonitor;
    }

    static void processLockExit(LockKey lockOrMonitor,
                                Position myPosition,
                                ActiveLockCollection mapContainingStack,
                                KeyToOperationCollection result) {
        LockStartOperation enter = mapContainingStack.pop(myPosition.threadIndex(), lockOrMonitor);
        if(enter != null) {
            result.addLockOrCondition(lockOrMonitor,
                    new Block(enter, new BlockEnd(myPosition)));
        }
    }

    @Override
    public void addToKeyToOperationCollection(Position myPosition,
                                              ActiveLockCollection mapContainingStack,
                                              KeyToOperationCollection result) {
        processLockExit(lockOrMonitor, myPosition, mapContainingStack, result);
    }

    @Override
    public int threadIndex() {
        return methodIdByteCodePositionAndThreadIndex.threadIndex();
    }

    @Override
    public String toString() {
        return "lockExit(" +
                methodIdByteCodePositionAndThreadIndex.threadIndex() +
                ","  + lockOrMonitor +
                ");";
    }

    @Override
    public boolean equalsNormalized(InterleaveAction other) {
        if(! (other instanceof LockExit)) {
            return false;
        }
        LockExit otherLock = (LockExit) other;
        if(! methodIdByteCodePositionAndThreadIndex.equals(otherLock.methodIdByteCodePositionAndThreadIndex))  {
            return false;
        }

        return lockOrMonitor.equalsNormalized(lockOrMonitor);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        LockExit lockExit = (LockExit) object;
        return Objects.equals(methodIdByteCodePositionAndThreadIndex, lockExit.methodIdByteCodePositionAndThreadIndex) && Objects.equals(lockOrMonitor, lockExit.lockOrMonitor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(),methodIdByteCodePositionAndThreadIndex, lockOrMonitor);
    }
}
