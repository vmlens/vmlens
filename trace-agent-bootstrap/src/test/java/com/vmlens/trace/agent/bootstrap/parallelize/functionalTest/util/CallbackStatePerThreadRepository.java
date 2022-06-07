package com.vmlens.trace.agent.bootstrap.parallelize.functionalTest.util;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.threadQueue.QueueCollection;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class CallbackStatePerThreadRepository {

    private List<CallbackStatePerThread> threadIndexToCallbackStatePerThread = new LinkedList();
    private final QueueCollection queueCollection = mock(QueueCollection.class);

    public static long threadId(int threadIndex) {
        return threadIndex * 10 + 1;
    }

    public CallbackStatePerThread get(int threadIndex) {
        while (threadIndexToCallbackStatePerThread.size() <= threadIndex) {
            CallbackStatePerThread callbackStatePerThread = new CallbackStatePerThread(true,
                    0, threadId(threadIndex), queueCollection, true, null);
            threadIndexToCallbackStatePerThread.add(callbackStatePerThread);
        }
        return threadIndexToCallbackStatePerThread.get(threadIndex);
    }

    public void reset() {
        List<CallbackStatePerThread> n = new LinkedList();
        n.add(get(0));
        threadIndexToCallbackStatePerThread = n;
    }

    public int threadIndex(long threadId) {
        int index = 0;
        for (CallbackStatePerThread callbackStatePerThread : threadIndexToCallbackStatePerThread) {
            if (callbackStatePerThread.threadId == threadId) {
                return index;
            }
            index++;
        }
        throw new RuntimeException("not found " + threadId);
    }
}
