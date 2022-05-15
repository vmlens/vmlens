package com.vmlens.trace.agent.bootstrap.parallelize.functionalTest;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.parallelize.testFixture.ThreadIndexToThreadId;
import com.vmlens.trace.agent.bootstrap.threadQueue.QueueCollection;

import java.util.LinkedList;

import static org.mockito.Mockito.mock;

public class CallbackStatePerThreadRepository implements ThreadIndexToThreadId {

    private final LinkedList<CallbackStatePerThread> threadIndexToCallbackStatePerThread = new LinkedList();
    private final QueueCollection queueCollection = mock(QueueCollection.class);
    private long currentThreadId = 1;

    public CallbackStatePerThread get(int threadIndex) {
        while (threadIndexToCallbackStatePerThread.size() <= threadIndex) {
            CallbackStatePerThread callbackStatePerThread = new CallbackStatePerThread(true,
                    0, currentThreadId, queueCollection, true, null);
            threadIndexToCallbackStatePerThread.add(callbackStatePerThread);
            currentThreadId += 10;
        }
        return threadIndexToCallbackStatePerThread.get(threadIndex);
    }


    @Override
    public long threadId(int threadIndex) {
        return get(threadIndex).threadId;
    }
}
