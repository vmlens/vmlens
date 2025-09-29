package com.vmlens.nottraced.agent.write;


import com.vmlens.transformed.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;

import java.util.concurrent.ThreadFactory;

public class WriteEventToFileThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable arg0) {
        return new Thread(arg0, ThreadLocalForParallelize.ANARSOFT_THREAD_NAME);
    }

}
