package com.vmlens.trace.agent.bootstrap.callback.intestaction.state;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public interface ExecuteAfterOperation {

    void execute(int inMethodId, int position,
                 ThreadLocalWhenInTest threadLocalDataWhenInTest,
                 QueueIn queueIn,
                 ReadWriteLockMap readWriteLockMap);

}
