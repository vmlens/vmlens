package com.vmlens.trace.agent.bootstrap.callback;


import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import org.jctools.queues.MpscArrayQueue;


public class EventQueue implements QueueIn {

    private final MpscArrayQueue queue;

    public EventQueue() {
        super();
        queue = new MpscArrayQueue(5000);
    }


    int writeEventCount = 0;

    public void offer(Object element) {
        ThreadLocalForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();

        queue.offer(element);

    }

    public MpscArrayQueue queue() {
        return queue;
    }
}