package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.FirstMethodInThread;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalForParallelizeProvider;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalForParallelizeProviderImpl;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.EventQueueSingleton;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.callbackStatePerThread;

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
            return ParallelizeFacade.parallelize().hasNext(callbackStatePerThread.get(),eventQueue, obj);
        } finally {
            threadLocal.decrementInsideVMLens();
        }
    }

    @Override
    public void initialize(InitializationAction initializationAction) {
        ThreadLocalForParallelize threadLocal = threadLocalForParallelizeProvider.threadLocalForParallelize();
        if(canProcess(threadLocal)) {
            try{
                threadLocal.incrementInsideVMLens();
                initializationAction.initialize();
            }
            finally {
                threadLocal.decrementInsideVMLens();
            }
        }
    }

    public boolean process(CallbackAction callbackAction) {
        ThreadLocalForParallelize threadLocal = threadLocalForParallelizeProvider.threadLocalForParallelize();
        if(canProcess(threadLocal)) {
            try{
                threadLocal.incrementInsideVMLens();
                Integer methodId = callbackAction.isFirstMethodInThread(checkIsThreadRun);
                if(methodId != null) {
                    FirstMethodInThread firstMethodInThread = new FirstMethodInThread(methodId,threadLocalForParallelizeProvider
                            .threadLocalForParallelize()
                            .stacktraceDepthProvider()
                            .getStacktraceDepth());
                    parallelizeFacade.newTask(
                            eventQueue,
                            threadLocal,
                            new ThreadWrapper(Thread.currentThread()),
                            firstMethodInThread);
                }
                ThreadLocalWhenInTest dataWhenInTest = threadLocal.getThreadLocalWhenInTest();
                if (dataWhenInTest != null) {
                    callbackAction.execute(new InTestActionProcessor(eventQueue,dataWhenInTest,threadLocal.stacktraceDepthProvider()));

                    if(callbackAction.couldBeLastMethodInThread(dataWhenInTest)) {
                        int stackTraceDepth = threadLocalForParallelizeProvider
                                .threadLocalForParallelize()
                                .stacktraceDepthProvider()
                                .getStacktraceDepth();
                        RuntimeEvent lastAction = callbackAction.isLastMethodInThread(dataWhenInTest, stackTraceDepth);
                        if(lastAction != null) {
                            dataWhenInTest.runAdapter().afterLastThreadAction(dataWhenInTest,eventQueue,lastAction);
                        }
                    }




                    return true;
                }
            } finally {
                threadLocal.decrementInsideVMLens();
            }
        }
        return false;
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



}
