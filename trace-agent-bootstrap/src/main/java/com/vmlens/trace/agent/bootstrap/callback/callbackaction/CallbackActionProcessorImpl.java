package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalForParallelizeProvider;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalForParallelizeProviderImpl;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.EventQueueSingleton;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.HasNextResult;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.*;

public class CallbackActionProcessorImpl implements CallbackActionProcessor {

    private final ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider;
    private final CheckIsThreadRun checkIsThreadRun;
    private final ParallelizeFacade parallelizeFacade;
    private final QueueIn eventQueue;

    public CallbackActionProcessorImpl() {
        this(new ThreadLocalForParallelizeProviderImpl(),
                CheckIsThreadRun.SINGLETON,
                ParallelizeFacade.parallelize(),
                EventQueueSingleton.eventQueue);
    }

    private CallbackActionProcessorImpl(ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider,
                                       CheckIsThreadRun checkIsThreadRun,
                                       ParallelizeFacade parallelizeFacade,
                                       QueueIn eventQueue) {
        this.threadLocalForParallelizeProvider = threadLocalForParallelizeProvider;
        this.checkIsThreadRun = checkIsThreadRun;
        this.parallelizeFacade = parallelizeFacade;
        this.eventQueue = eventQueue;
    }

    public void startDoNotTrace() {
        startDoNotTrace(threadLocalForParallelizeProvider.threadLocalForParallelize());
    }

    public void endDoNotTrace() {
        endDoNotTrace(threadLocalForParallelizeProvider.threadLocalForParallelize());
    }

    public void vmlensApiClose(Object obj) {
        ThreadLocalForParallelize threadLocal = threadLocalForParallelizeProvider.threadLocalForParallelize();
        threadLocal.incrementInsideVMLens();
        try {
            parallelizeFacade.close(callbackStatePerThread.get(), obj);
        } finally {
            threadLocal.decrementInsideVMLens();
        }
    }

    public boolean vmlensApiHasNext(Object obj) {
        ThreadLocalForParallelize threadLocal = threadLocalForParallelizeProvider.threadLocalForParallelize();
        threadLocal.incrementInsideVMLens();
        try {
            HasNextResult result = ParallelizeFacade.parallelize().hasNext(callbackStatePerThread.get(), obj);
            eventQueue.offer(result.serializableEvents());
            return result.hasNext();
        } finally {
            threadLocal.decrementInsideVMLens();
        }
    }

    public boolean process(CallbackAction callbackAction) {
       return process(callbackAction,false);
    }

    public void processWithCheckNewThread(CallbackAction callbackAction) {
        process(callbackAction,true);
    }

    // visible for test
    void startDoNotTrace(ThreadLocalForCallbackAction threadLocalForCallbackAction) {
        threadLocalForCallbackAction.startDoNotTrace();
    }

    void endDoNotTrace(ThreadLocalForCallbackAction threadLocalForCallbackAction) {
        threadLocalForCallbackAction.endDoNotTrace();
    }

    boolean canProcess(ThreadLocalForCallbackAction threadLocalForCallbackAction) {
        return threadLocalForCallbackAction.canProcess();
    }

    private boolean process(CallbackAction callbackAction, boolean checkNewThread) {
        ThreadLocalForParallelize threadLocal = threadLocalForParallelizeProvider.threadLocalForParallelize();
        if(canProcess(threadLocal)) {
            try{
                threadLocal.incrementInsideVMLens();
                if(checkNewThread) {
                    if(checkIsThreadRun.isThreadRun()) {
                        parallelizeFacade.newTask(
                                eventQueue,
                                threadLocal,
                                new ThreadWrapper(Thread.currentThread()));
                    }
                }
                ThreadLocalWhenInTest dataWhenInTest = threadLocal.getThreadLocalWhenInTest();
                if (dataWhenInTest != null) {
                    callbackAction.execute(new InTestActionProcessor(eventQueue,dataWhenInTest,threadLocal.stacktraceDepthProvider()));
                    return true;
                }
            } finally {
                threadLocal.decrementInsideVMLens();
            }
        }
        return false;
    }

}
