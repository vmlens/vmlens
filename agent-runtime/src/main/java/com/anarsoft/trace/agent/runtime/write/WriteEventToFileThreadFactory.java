package com.anarsoft.trace.agent.runtime.write;


import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThreadForParallelize;

import java.util.concurrent.ThreadFactory;

public class WriteEventToFileThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable arg0) {
        Thread thread = new Thread(arg0, CallbackStatePerThreadForParallelize.ANARSOFT_THREAD_NAME);
        thread.setDaemon(true);
        return thread;
    }

}
