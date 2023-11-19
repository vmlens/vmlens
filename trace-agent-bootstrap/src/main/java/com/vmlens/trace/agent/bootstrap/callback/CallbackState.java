package com.vmlens.trace.agent.bootstrap.callback;


public class CallbackState {

    public static final EventQueue eventQueue = new EventQueue();

    public static final ThreadLocal<CallbackStatePerThreadForParallelize> callbackStatePerThread = new ThreadLocal<CallbackStatePerThreadForParallelize>() {
        @Override
        protected CallbackStatePerThreadForParallelize initialValue() {
            return new CallbackStatePerThreadForParallelize(Thread.currentThread().getId(), eventQueue);
        }
    };


}
