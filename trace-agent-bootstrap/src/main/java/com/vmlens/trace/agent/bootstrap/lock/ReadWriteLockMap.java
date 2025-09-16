package com.vmlens.trace.agent.bootstrap.lock;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.ReadWriteLockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.ReentrantLockKey;
import gnu.trove.map.hash.TLongLongHashMap;

public class ReadWriteLockMap {

    public static final ReadWriteLockMap INSTANCE = new ReadWriteLockMap();

    private final TLongLongHashMap conditionToLockHashCode = new TLongLongHashMap();
    private final TLongLongHashMap dependentToUnderlying = new TLongLongHashMap();

    public synchronized long getUnderlying(long dependent) {
        return dependentToUnderlying.get(dependent);
    }

    public synchronized void addUnderlying(long dependent, long underlying) {
        dependentToUnderlying.put(dependent,underlying);
    }

    public synchronized void addCondition(long condition, long lock) {
        conditionToLockHashCode.put(condition,lock);
    }

    /**
     * could be solved differently since we have the lock type when we add the condition,
     * see com.vmlens.classmodel.model.PreAnalyzed Lock
     */

    public synchronized LockKey lockKeyForCondition(long conditionHashCode) {
        long lockHashCode = conditionToLockHashCode.get(conditionHashCode);
        if(dependentToUnderlying.containsKey(lockHashCode)) {
            return ReadWriteLockKey.writeKey(dependentToUnderlying.get(lockHashCode));
        }
        return new ReentrantLockKey(lockHashCode);
    }

}
