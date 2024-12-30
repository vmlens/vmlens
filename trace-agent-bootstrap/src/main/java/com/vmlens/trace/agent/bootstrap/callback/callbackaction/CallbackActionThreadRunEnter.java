package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.emptyList;

public class CallbackActionThreadRunEnter implements CallbackAction {

    private final RunnableOrThreadWrapper runnableOrThreadWrapper;
    private final ThreadLocalForParallelize threadLocalForParallelize;

    public CallbackActionThreadRunEnter(RunnableOrThreadWrapper runnableOrThreadWrapper,
                                        ThreadLocalForParallelize threadLocalForParallelize) {
        this.runnableOrThreadWrapper = runnableOrThreadWrapper;
        this.threadLocalForParallelize = threadLocalForParallelize;
    }

    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> execute(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        threadLocalDataWhenInTest.runAdapter().newTask(runnableOrThreadWrapper, threadLocalForParallelize);
        return emptyList();
    }
}
