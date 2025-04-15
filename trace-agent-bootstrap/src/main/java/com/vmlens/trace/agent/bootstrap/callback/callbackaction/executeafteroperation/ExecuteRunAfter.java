package com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeafteroperation;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.WithInMethodIdAndPosition;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class ExecuteRunAfter <EVENT extends RuntimeEvent & WithInMethodIdAndPosition>
        implements ExecuteAfterOperation {

    private final EVENT event;

    public ExecuteRunAfter(EVENT event) {
        this.event = event;

    }

    @Override
    public void execute(int inMethodId, int position,
                        ThreadLocalWhenInTest threadLocalDataWhenInTest,
                        QueueIn queueIn,
                        ReadWriteLockMap readWriteLockMap) {
        event.setInMethodIdAndPosition(inMethodId, position, readWriteLockMap);
        threadLocalDataWhenInTest.runAdapter().after(event,threadLocalDataWhenInTest, queueIn);
    }
}
