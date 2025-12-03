package com.vmlens.trace.agent.bootstrap.callback.intestaction.state;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.AfterContext;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.WithInMethodIdPositionReadWriteLockMap;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class ExecuteRunAfter <EVENT extends RuntimeEvent & WithInMethodIdPositionReadWriteLockMap>
        implements ExecuteAfterOperation {

    private final EVENT event;

    public ExecuteRunAfter(EVENT event) {
        this.event = event;
    }

    @Override
    public void execute(int inMethodId,
                        int position,
                        ThreadLocalWhenInTest threadLocalDataWhenInTest,
                        QueueIn queueIn,
                        ReadWriteLockMap readWriteLockMap) {
        event.setInMethodIdAndPosition(inMethodId, position, readWriteLockMap);
        threadLocalDataWhenInTest.runAdapter().after(
                new AfterContext(threadLocalDataWhenInTest,event,queueIn));
    }
}
