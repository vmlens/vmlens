package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import gnu.trove.set.hash.TLongHashSet;

public class AlreadyCheckedThreadIds {

    private final Object LOCK = new Object();
    private final TLongHashSet alreadyChecked = new TLongHashSet();

    public boolean containsAndAddThreadId() {
        long threadId = Thread.currentThread().getId();
        synchronized(LOCK) {
            if(alreadyChecked.contains(threadId)) {
                return true;
            }
            alreadyChecked.add(threadId);
            return false;
        }
    }

}
