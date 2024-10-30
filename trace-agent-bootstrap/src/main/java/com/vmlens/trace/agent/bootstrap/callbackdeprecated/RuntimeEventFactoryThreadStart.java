package com.vmlens.trace.agent.bootstrap.callbackdeprecated;

import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEventFactory;
import com.vmlens.trace.agent.bootstrap.event.impl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public class RuntimeEventFactoryThreadStart implements RuntimeEventFactory {

    private final Object newThread;

    public RuntimeEventFactoryThreadStart(Object newThread) {
        this.newThread = newThread;
    }

    @Override
    public RuntimeEvent create() {
        return new ThreadStartEvent(new RunnableOrThreadWrapper(newThread));
    }
}
